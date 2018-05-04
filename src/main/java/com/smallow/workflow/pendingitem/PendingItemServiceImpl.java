package com.smallow.workflow.pendingitem;


import com.smallow.utils.KeyUtil;
import com.smallow.workflow.dto.ApprovalProcessDto;
import com.smallow.workflow.entity.ApprovalStep;
import com.smallow.workflow.enums.BusinessEnumString;
import com.smallow.workflow.enums.PendingItemStatusEnum;
import com.smallow.workflow.enums.ResultEnum;
import com.smallow.workflow.exception.WorkFlowException;
import com.smallow.workflow.mapper.PendingItemMapper;
import com.smallow.workflow.service.ApprovalService;
import com.smallow.workflow.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by wanghuidong on 2018/5/3.
 */
@Service
@Slf4j
public class PendingItemServiceImpl implements PendingService {

    @Autowired
    private PendingItemMapper pendingItemMapper;
    @Autowired
    private ApprovalService approvalService;

    @Override
    @Transactional
    public PendingItem create(Object workData, Integer workId, Integer userId, Integer status, String memo, String businessCode) {
        /**
         * 根据业务类型代码查找关联的流程信息
         */
        ApprovalProcessDto approvalProcessDto = approvalService.findByBusinessCode(businessCode);
        List<ApprovalStep> stepList = approvalProcessDto.getApprovalSteps();
        stepList.sort((a1, a2) -> a1.getOrder().compareTo(a2.getOrder()));

        /**
         * 先根据workId查找是否存在待办
         */
        PendingItem pendingItem = findByWorkId(workId);
        Integer approvalStepId = 0;
        if (pendingItem == null) {
            pendingItem = new PendingItem();
            /**
             * 如果是新待办则步骤id为第一步
             */
            approvalStepId = stepList.get(0).getId();
        } else {
            /**
             * 否则需要根据当前步骤id查找下一步的步骤id
             */
            approvalStepId = findNextStepByCurrentStepId(pendingItem.getApprovalProcessStepId(), status, stepList);
            if (approvalStepId == 0) {
                throw new WorkFlowException(ResultEnum.PENDING_ITEM_NEXT_STEP_NOT_EXIST);
            }
        }
        pendingItem.setId(KeyUtil.genUniqueKey());
        pendingItem.setWorkId(workId);
        pendingItem.setTitle(EnumUtil.getByCode(businessCode, BusinessEnumString.class).getName());
        pendingItem.setBusinessCode(approvalProcessDto.getBusinessCode());
        pendingItem.setBusinessName(approvalProcessDto.getBusinessName());
        pendingItem.setApprovalProcessId(approvalProcessDto.getId());
        pendingItem.setApprovalProcessStepId(approvalStepId);
        pendingItem.setUserId(userId);
        pendingItem.setMemo(memo);
        pendingItem.setStatus(status);
        pendingItemMapper.save(pendingItem);
        return pendingItem;
    }

    @Override
    public PendingItem findByWorkId(Integer workId) {
        return pendingItemMapper.findByWorkId(workId);
    }


    private Integer findNextStepByCurrentStepId(Integer currentStepId, Integer status, List<ApprovalStep> stepList) {
        Integer nextStepId = 0;
        List<Integer> list = new ArrayList<>();
        stepList.stream().filter(findCurrentStepById.apply(currentStepId)).findFirst().ifPresent(
                currentStep -> {
                    log.info("当前处在步骤={}", currentStep.getName());
                    if (status.intValue() == PendingItemStatusEnum.PASSED.getCode()) {
                        //如果通过 走下一步流程节点
                        stepList.stream().filter(findNextStep.apply(currentStep)).findFirst().ifPresent(
                                nextStep -> {
                                    list.add(nextStep.getId());
                                }
                        );
                    } else {
                        //没有通过 stepId不变
                        list.add(currentStepId);
                    }

                }
        );
        if (!CollectionUtils.isEmpty(list))
            nextStepId = list.get(0);
        return nextStepId;
    }

    final Function<Integer, Predicate<ApprovalStep>> findCurrentStepById = currentId -> step -> step.getId().intValue() == currentId;
    final Function<ApprovalStep, Predicate<ApprovalStep>> findNextStep = currentStep -> step -> step.getOrder() == currentStep.getOrder() + 1;
}
