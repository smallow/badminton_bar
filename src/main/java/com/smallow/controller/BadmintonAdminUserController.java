package com.smallow.controller;

import com.smallow.constant.CookieConstant;
import com.smallow.constant.RedisConstant;
import com.smallow.entity.Group;
import com.smallow.enums.BadmintonGroupCheckEnum;
import com.smallow.enums.GroupStatusEnum;
import com.smallow.enums.ResultEnum;
import com.smallow.exception.BadmintonException;
import com.smallow.service.GroupService;
import com.smallow.service.WxMpQrcodeService2;
import com.smallow.utils.CookieUtil;
import com.smallow.utils.KeyUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * Created by wanghuidong on 2017/8/23.
 */
@Controller
@RequestMapping("/admin")
public class BadmintonAdminUserController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private GroupService groupService;
    @Autowired
    private WxMpQrcodeService2 wxMpQrcodeService2;
    @Autowired
    private WxMpService wxMpService;


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("admin/login");
    }

    @GetMapping("/realCertify")
    public ModelAndView realCertify(@RequestParam("customerId") String customerId,
                                    @RequestParam("merchantCode") String merchantCode,
                                    @RequestParam("notifyUrl") String notifyUrl,
                                    @RequestParam("backUrl") String backUrl,
                                    Map<String, Object> map
    ) {
        map.put("customerId", customerId);
        map.put("notifyUrl", notifyUrl);
        map.put("backUrl", backUrl);
        return new ModelAndView("admin/realCertify");
    }

    @PostMapping("/getWxData")
    @ResponseBody
    public Map<String, Object> getWxData(@RequestParam("notifyUrl") String notifyUrl,
                                         @RequestParam("customerId") String customerId) {
        Map<String, Object> map = new HashMap<>();

        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        //也支持中文
        params.add("customerId", customerId);
        params.add("name", "王会东");
        params.add("frontpic", "frontpic-------12121212ddd");
        params.add("backpic", "backpic*********sddsdd");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        //执行HTTP请求
        ResponseEntity<String> response = client.exchange(notifyUrl, HttpMethod.POST, requestEntity, String.class);
        //输出结果
        System.out.println(response.getBody());
        map.put("success", true);
        return map;
    }

    @PostMapping("/getQrCode")
    @ResponseBody
    public Map<String, String> getQrCode() {
        Map<String, String> map = new HashMap<>();
        map.put("qr_code", KeyUtil.genUniqueKey());
        return map;
    }


    @GetMapping("/getQrCodePic/{scene_str}")
    public void qrAuthorize(@PathVariable("scene_str") String sceneStr,
                            HttpServletResponse response) {
        try {
            WxMpQrCodeTicket ticket = wxMpQrcodeService2.qrCodeCreateTmpTicket(sceneStr, RedisConstant.QRCODE_EXPIRE);
            File file = wxMpService.getQrcodeService().qrCodePicture(ticket);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            inputStream.read(data);
            inputStream.close();
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-Type", "image/jpg");//设置响应的媒体类型，这样浏览器会识别出响应的是图片
            response.getOutputStream().write(data);
            out.flush();
            out.close();
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new BadmintonException(ResultEnum.QRCODE_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BadmintonException(ResultEnum.QRCODE_ERROR);
        }
    }


    @PostMapping("/login")
    public ModelAndView login(@RequestParam("scene_str") String scene_str,
                              Map<String, Object> map, HttpServletRequest request,
                              HttpServletResponse response) {
        if (!StringUtils.isEmpty(scene_str)) {
            String openid = redisTemplate.opsForValue().get(String.format(RedisConstant.QRCODE_PREFIX, scene_str));
            if (StringUtils.isEmpty(openid)) {
                map.put("msg", ResultEnum.LOGIN_QRCODE_TIMEOUT.getMessage());
                map.put("url", "/badminton/admin/group/list");
                return new ModelAndView("common/error");
            }

            Map<String, Object> param = new HashMap<>();
            PageRequest pageRequest = new PageRequest(0, 10);
            param.put("openid", openid);
            Page page = groupService.findList(param, pageRequest);
            List<Group> list = page.getContent();
            if (list == null || list.isEmpty()) {
                map.put("msg", ResultEnum.ADMIN_EMPTY.getMessage());
                map.put("url", "/badminton/admin/group/list");
                return new ModelAndView("common/error");
            }

            //2. 设置token至redis
            String token = UUID.randomUUID().toString();
            Integer expire = RedisConstant.TOKEN_EXPIRE;
            redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

            //3. 设置token至cookie
            CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

            request.getSession().setAttribute("openid", openid);
            request.getSession().setAttribute("groupList", list);
            return new ModelAndView("redirect:main/adminMain");
        }
        map.put("msg", ResultEnum.LOGIN_ILLEGAL.getMessage());
        map.put("url", "/badminton/admin/group/list");
        return new ModelAndView("common/error");
    }


}
