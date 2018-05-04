package com.smallow.workflow.example.qingjia;

import org.springframework.stereotype.Component;

/**
 * Created by wanghuidong on 2018/4/27.
 */
@Component(value = "qingjiaMapper")
public interface QingJiaMapper {

    Integer insert(QingJia qingJia);


}
