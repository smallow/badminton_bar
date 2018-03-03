package com.smallow.service.impl;

import com.smallow.service.ConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghuidong on 2018/3/2.
 */
@Service
public class ConstantServiceImpl implements ConstantService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, List<Map<String, Object>>> findConstant() {
        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        List<Map<String, Object>> province = jdbcTemplate.queryForList("SELECT * from bd_province order by create_time desc");
        map.put("province", province);

        List<Map<String, Object>> city = jdbcTemplate.queryForList("SELECT * from bd_city order by create_time desc");
        map.put("city", city);

        List<Map<String, Object>> area = jdbcTemplate.queryForList("SELECT * from bd_area order by create_time desc");
        map.put("area", area);

        List<Map<String, Object>> arena = jdbcTemplate.queryForList("SELECT * from bd_arena order by create_time desc");
        map.put("arena", arena);
        return map;
    }
}
