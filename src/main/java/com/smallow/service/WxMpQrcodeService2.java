package com.smallow.service;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpQrcodeService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * Created by wanghuidong on 2017/8/25.
 */
@Component
public class WxMpQrcodeService2 implements WxMpQrcodeService {


    @Autowired
    private WxMpService wxMpService;

    public WxMpQrCodeTicket qrCodeCreateTmpTicket(String str, Integer expireSeconds) throws WxErrorException {
        if(StringUtils.isEmpty(str)) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(-1).setErrorMsg("临时二维码字符串参数不能为空！").build());
        } else if(expireSeconds != null && expireSeconds.intValue() > 2592000) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(-1).setErrorMsg("临时二维码有效时间最大不能超过2592000（即30天）！").build());
        } else {
            if(expireSeconds == null) {
                expireSeconds = Integer.valueOf(30);
            }
            String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
            JsonObject json = new JsonObject();
            json.addProperty("action_name", "QR_STR_SCENE");
            json.addProperty("expire_seconds", expireSeconds);
            JsonObject actionInfo = new JsonObject();
            JsonObject scene = new JsonObject();
            scene.addProperty("scene_str", str);
            actionInfo.add("scene", scene);
            json.add("action_info", actionInfo);
            String responseContent = wxMpService.post(url, json.toString());
            return WxMpQrCodeTicket.fromJson(responseContent);
        }
    }



    @Override
    public WxMpQrCodeTicket qrCodeCreateTmpTicket(int i, Integer integer) throws WxErrorException {
        return wxMpService.getQrcodeService().qrCodeCreateTmpTicket(i,integer);
    }

    @Override
    public WxMpQrCodeTicket qrCodeCreateLastTicket(int i) throws WxErrorException {
        return wxMpService.getQrcodeService().qrCodeCreateLastTicket(i);
    }

    @Override
    public WxMpQrCodeTicket qrCodeCreateLastTicket(String s) throws WxErrorException {
        return wxMpService.getQrcodeService().qrCodeCreateLastTicket(s);
    }

    @Override
    public File qrCodePicture(WxMpQrCodeTicket wxMpQrCodeTicket) throws WxErrorException {
        return wxMpService.getQrcodeService().qrCodePicture(wxMpQrCodeTicket);
    }

    @Override
    public String qrCodePictureUrl(String s, boolean b) throws WxErrorException {
        return wxMpService.getQrcodeService().qrCodePictureUrl(s,b);
    }

    @Override
    public String qrCodePictureUrl(String s) throws WxErrorException {
        return wxMpService.getQrcodeService().qrCodePictureUrl(s);
    }
}
