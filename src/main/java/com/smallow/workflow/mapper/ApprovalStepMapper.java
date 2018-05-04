package com.smallow.workflow.mapper;

import com.smallow.workflow.entity.ApprovalStep;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wanghuidong on 2018/5/3.
 */
@Component(value = "approvalStepMapper")
public interface ApprovalStepMapper {


    List<ApprovalStep> findByApprovalId(Integer approvalId);
}
