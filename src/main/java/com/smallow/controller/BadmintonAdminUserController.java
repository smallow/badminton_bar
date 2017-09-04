package com.smallow.controller;

import com.smallow.constant.RedisConstant;
import com.smallow.entity.Group;
import com.smallow.enums.BadmintonGroupCheckEnum;
import com.smallow.enums.GroupStatusEnum;
import com.smallow.enums.ResultEnum;
import com.smallow.exception.BadmintonException;
import com.smallow.service.GroupService;
import com.smallow.service.WxMpQrcodeService2;
import com.smallow.utils.KeyUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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


/**
 * Created by wanghuidong on 2017/8/23.
 */
@Controller
@RequestMapping("/admin")
public class BadmintonAdminUserController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private WxMpQrcodeService2 wxMpQrcodeService2;
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private GroupService groupService;


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("admin/login");
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


    @PostMapping("/adminMain")
    public ModelAndView adminMain(@RequestParam("scene_str") String scene_str,
                                  Map<String, Object> map, HttpServletRequest request) {
        if (!StringUtils.isEmpty(scene_str)) {
            String openid = redisTemplate.opsForValue().get(String.format(RedisConstant.QRCODE_PREFIX, scene_str));
            if (StringUtils.isEmpty(openid)) {
                map.put("msg", ResultEnum.LOGIN_QRCODE_TIMEOUT.getMessage());
                map.put("url", "/badminton/admin/group/list");
                return new ModelAndView("common/error");
            }

            //Page page = groupService.findGroupInfoByOpenid(openid);
            List<Group> list = null;
            if (list == null || list.isEmpty()) {
                map.put("msg", ResultEnum.ADMIN_EMPTY.getMessage());
                map.put("url", "/badminton/admin/group/list");
                return new ModelAndView("common/error");
            }
            request.getSession().setAttribute("openid", list.get(0).getOpenid());
            request.getSession().setAttribute("groupList", list);
            return new ModelAndView("admin/main");
        }
        map.put("msg", ResultEnum.LOGIN_ILLEGAL.getMessage());
        map.put("url", "/badminton/admin/group/list");
        return new ModelAndView("common/error");
    }
}
