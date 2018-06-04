package com.smallow.workflow.service.impl;


import com.smallow.workflow.dto.ApprovalProcessDto;
import com.smallow.workflow.entity.ApprovalStep;
import com.smallow.workflow.enums.PendingItemStatusEnum;
import com.smallow.workflow.enums.WorkFlowDealTypeEnum;
import com.smallow.workflow.mapper.PendingItemMapper;
import com.smallow.workflow.entity.PendingItem;
import com.smallow.workflow.service.ApprovalService;
import com.smallow.workflow.service.PendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public void save(PendingItem pendingItem) {
        pendingItemMapper.save(pendingItem);

    }

    public PendingItem findLastPendingItem(Integer workId) {
        Map<String, Object> param = new HashMap<String, Object>() {{
            put("workId", workId);
        }};
        List<PendingItem> list = findList(param, new PageRequest(0, 1)).getContent();
        if (!CollectionUtils.isEmpty(list))
            return list.get(0);
        return null;
    }

    @Override
    public Page<PendingItem> findList(Map<String, Object> param, Pageable pageable) {
        param.put("offset", pageable.getOffset());
        param.put("pageSize", pageable.getPageSize());
        List<PendingItem> list = pendingItemMapper.findList(param);
        long totalCount = pendingItemMapper.count(param);
        return new PageImpl<PendingItem>(list, pageable, totalCount);
    }

    /**
     * 根据stepID判断是否是最后步骤
     *
     * @param currentStepId
     * @param stepList
     * @return
     */
    public boolean isLastStep(Integer currentStepId, List<ApprovalStep> stepList) {
        stepList.sort((step1, step2) -> step2.getOrder().compareTo(step1.getOrder()));
        ApprovalStep finishedStep = stepList.get(0);
        if (finishedStep.getId().intValue() == currentStepId.intValue())
            return true;
        return false;
    }

    public Integer findNextStepByCurrentStepId(Integer currentStepId, Integer dealType, List<ApprovalStep> stepList) {
        Integer nextStepId = 0;
        List<Integer> list = new ArrayList<>();
        stepList.stream().filter(findCurrentStepById.apply(currentStepId)).findFirst().ifPresent(
                currentStep -> {
                    log.info("当前处在步骤={}", currentStep.getName());
                    if (dealType.intValue() == WorkFlowDealTypeEnum.PASS.getCode()) {
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
