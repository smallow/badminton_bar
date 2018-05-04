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
    public List<Map<String, Object>> findAreaConstants() {
        return jdbcTemplate.queryForList("SELECT * from bd_area order by create_time desc");
    }
}
