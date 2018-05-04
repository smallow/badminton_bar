package com.smallow.service.impl;

import com.smallow.workflow.entity.ApprovalStep;
import com.smallow.workflow.example.qingjia.QingJia;
import com.smallow.workflow.example.qingjia.QingJiaServiceImpl;
import com.smallow.workflow.pendingitem.PendingItem;
import com.smallow.workflow.pendingitem.PendingItemServiceImpl;
import com.smallow.workflow.service.impl.ApprovalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by wanghuidong on 2018/4/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QingJiaRepositoryTest {


    @Autowired
    private QingJiaServiceImpl qingJiaService;


    @Autowired
    private PendingItemServiceImpl pendingItemService;

    @Test
    public void save() throws Exception {
        log.info("首先生成相关业务数据:{}", "\"请假业务数据\"");
//        QingJia qingJia1 = new QingJia();
//        qingJia1.setTitle("互联网事业部+王会东+请假单+2020.05.04一天");
//        qingJia1.setContent("家中有事2");
//        Integer qingjiaId = qingJiaService.save(qingJia1);
//        log.info("\"请假业务数据\"生成成功,id={}", qingjiaId + "..........开始关联流程生成待办数据");
        String businessCode = "1001";
        String memo="准予通过";
        pendingItemService.create(null, 15, 3,0,memo, businessCode);

    }


}
