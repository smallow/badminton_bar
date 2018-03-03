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
        Map<String, List<Map<String, Object>>> map = constantService.findConstant();
        if (map != null && map.size() > 0) {
            AdministrativeDivision.setProvince(map.get("province"));
            AdministrativeDivision.setArea(map.get("area"));
            AdministrativeDivision.setCity(map.get("city"));
            AdministrativeDivision.setArena(map.get("arena"));
            log.info("【加载省份数据成功】");
        }


    }
}
