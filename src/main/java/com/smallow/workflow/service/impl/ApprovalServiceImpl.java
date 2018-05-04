package com.smallow.workflow.service.impl;

import com.smallow.workflow.dto.ApprovalProcessDto;
import com.smallow.workflow.entity.ApprovalProcess;
import com.smallow.workflow.entity.ApprovalStep;
import com.smallow.workflow.enums.ResultEnum;
import com.smallow.workflow.exception.WorkFlowException;
import com.smallow.workflow.mapper.ApprovalMapper;
import com.smallow.workflow.mapper.ApprovalStepMapper;
import com.smallow.workflow.service.ApprovalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wanghuidong on 2018/5/2.
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private ApprovalMapper approvalMapper;
    @Autowired
    private ApprovalStepMapper stepMapper;

    @Override
    public ApprovalProcessDto findOne(Integer id) {
        ApprovalProcess approvalProcess = approvalMapper.findOne(id);
        return convertBean2Dto(approvalProcess);
    }

    @Override
    public ApprovalProcessDto findByBusinessCode(String businessCode) {
        ApprovalProcess approvalProcess = approvalMapper.findByBusinessCode(businessCode);
        return convertBean2Dto(approvalProcess);
    }

    private ApprovalProcessDto convertBean2Dto(ApprovalProcess approvalProcess) {
        if (approvalProcess == null) {
            throw new WorkFlowException(ResultEnum.APPROVAL_PROCESS_NOT_EXIST);
        }
        List<ApprovalStep> approvalStepList = stepMapper.findByApprovalId(approvalProcess.getId());
        if (CollectionUtils.isEmpty(approvalStepList)) {
            throw new WorkFlowException(ResultEnum.APPROVAL_STEP_NOT_EXIST);
        }
        ApprovalProcessDto approvalProcessDto = new ApprovalProcessDto();
        BeanUtils.copyProperties(approvalProcess, approvalProcessDto);
        approvalProcessDto.setApprovalSteps(approvalStepList);
        return approvalProcessDto;
    }
}
