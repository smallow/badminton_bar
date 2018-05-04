package com.smallow.workflow.example.qingjia;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghuidong on 2018/4/27.
 */
@Service
@Slf4j
public class QingJiaServiceImpl implements QingJiaService {
    @Autowired
    private QingJiaMapper qingJiaMapper;


    @Override
    @Transactional
    public Integer save(QingJia qingJia) throws Exception {
        qingJiaMapper.insert(qingJia);
        return qingJia.getId();
    }
}
