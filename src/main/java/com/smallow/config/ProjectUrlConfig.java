package com.smallow.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wanghuidong on 2017/8/23.
 */
@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {

    /**
     * 微信公众平台授权url
     */
    public String wechatMpAuthorize;

//    /**
//     * 微信开放平台授权url
//     */
//    public String wechatOpenAuthorize;

    /**
     * 系统本身
     */
    public String badminton;
}
