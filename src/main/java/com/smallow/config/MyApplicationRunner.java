package com.smallow.config;


import com.smallow.service.ConstantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * Created by wanghuidong on 2018/3/2.
 */
@Component
@Slf4j
@Order(value = 1)
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    private ConstantService constantService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("【开始加载代码表数据...】");
        List<Map<String, Object>> list = constantService.findAreaConstants();
        if (list != null && list.size() > 0) {
            AdministrativeDivision.setArea(list);
            log.info("【加载行政区划代码表数据成功】");
        }


    }
}
