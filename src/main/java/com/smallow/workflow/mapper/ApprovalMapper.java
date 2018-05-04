package com.smallow.workflow.mapper;

import com.smallow.workflow.entity.ApprovalProcess;
import org.springframework.stereotype.Component;

/**
 * Created by wanghuidong on 2018/5/2.
 */
@Component(value = "approvalMapper")
public interface ApprovalMapper {


    ApprovalProcess findOne(Integer id);

    ApprovalProcess findByBusinessCode(String businessCode);
}
