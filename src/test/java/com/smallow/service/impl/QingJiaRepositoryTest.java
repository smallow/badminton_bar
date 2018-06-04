package com.smallow.service.impl;

import com.smallow.utils.KeyUtil;
import com.smallow.workflow.dto.ApprovalProcessDto;
import com.smallow.workflow.entity.ApprovalProcess;
import com.smallow.workflow.entity.ApprovalStep;
import com.smallow.workflow.entity.PendingItem;
import com.smallow.workflow.entity.WorkFlow;
import com.smallow.workflow.enums.PendingItemStatusEnum;
import com.smallow.workflow.enums.WorkFlowDealTypeEnum;
import com.smallow.workflow.example.qingjia.QingJia;
import com.smallow.workflow.example.qingjia.QingJiaServiceImpl;
import com.smallow.workflow.service.ApprovalService;
import com.smallow.workflow.service.impl.ApprovalServiceImpl;
import com.smallow.workflow.service.impl.PendingItemServiceImpl;
import com.smallow.workflow.service.impl.WorkFlowServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
    private ApprovalServiceImpl approvalService;
    private String businessCode = "1001";
    private Integer user1 = 1;
    private Integer user2 = 2;
    private Integer user3 = 3;

    private Integer weichuli = 0;
    private Integer tongguo = 1;
    private Integer jujue = 2;
    private Integer zhongzhi = 3;


    private Integer sign_new = 0;
    private Integer sign_update = 1;


    @Autowired
    private PendingItemServiceImpl pendingItemService;

    @Autowired
    private WorkFlowServiceImpl workFlowService;




    @Test
    public void save() throws Exception {
        log.info("首先生成相关业务数据:{}", "\"请假业务数据\"");
        QingJia qingJia1 = new QingJia();
        qingJia1.setTitle("互联网事业部+王会东+请假单+2020.05.07一天");
        qingJia1.setContent("家中有事0507");
        Integer qingjiaId = qingJiaService.save(qingJia1);
        log.info("\"请假业务数据\"生成成功,id={}", qingjiaId + "..........开始关联流程生成待办数据");


        ApprovalProcessDto approvalProcessDto = approvalService.findByBusinessCode(businessCode);
        List<ApprovalStep> stepList = approvalProcessDto.getApprovalSteps();
        stepList.sort((a1, a2) -> a1.getOrder().compareTo(a2.getOrder()));

        createWorkFlow(qingjiaId, approvalProcessDto.getId(), );
        PendingItem pendingItem = createPtm(qingjiaId, businessCode, user1, user2, weichuli);
    }

    @Test
    public void dealPtm() throws Exception {
        PendingItem pendingItem = createPtm(37, businessCode, user2, user3, tongguo);
        updateWorkFlow(user2, tongguo, "通过", user3);

    }

    private void updateWorkFlow(Integer dealUserId, Integer dealType, String dealOption, Integer nextUserId) {

    }


    private PendingItem createPtm(Integer workId, String businessCode, Integer sendUserId, Integer dealUserId, Integer dealType) {
        ApprovalProcessDto approvalProcessDto = approvalService.findByBusinessCode(businessCode);
        List<ApprovalStep> stepList = approvalProcessDto.getApprovalSteps();
        stepList.sort((a1, a2) -> a1.getOrder().compareTo(a2.getOrder()));

        PendingItem pendingItem = new PendingItem();
        pendingItem.setTitle(businessCode + "-----");
        pendingItem.setId(KeyUtil.genUniqueKey());
        pendingItem.setWorkId(workId);
        pendingItem.setApprovalProcessId(approvalProcessDto.getId());
        pendingItem.setBusinessCode(businessCode);
        PendingItem lastPendingItem = pendingItemService.findLastPendingItem(workId);
        Integer approvalStepId = 0;
        if (lastPendingItem == null) {
            approvalStepId = stepList.get(0).getId();
        } else {
            Integer lastePendingItemStepId = lastPendingItem.getApprovalProcessStepId();
            approvalStepId = pendingItemService.findNextStepByCurrentStepId(lastePendingItemStepId, dealType, stepList);
        }
        pendingItem.setApprovalProcessStepId(approvalStepId);
        pendingItem.setStatus(PendingItemStatusEnum.PENDING.getCode());
        pendingItem.setSendUserId(sendUserId);
        pendingItem.setUserId(dealUserId);
        pendingItem.setSendTime(new Timestamp(new Date().getTime()));
        pendingItemService.save(pendingItem);
        return pendingItem;
    }


    private void createWorkFlow(Integer workId, ApprovalProcessDto approvalProcessDto, Integer currentStepId, Integer dealType) {
        List<ApprovalStep> stepList = approvalProcessDto.getApprovalSteps();
        stepList.sort((a1, a2) -> a1.getOrder().compareTo(a2.getOrder()));

        WorkFlow workFlow = new WorkFlow();
        workFlow.setId(KeyUtil.genUniqueKey());
        workFlow.setWorkId(workId);
        workFlow.setApprovalProcessId(approvalProcessDto.getId());

        Integer approvalProcessStepId = currentStepId == null ? stepList.get(0).getId() : getNextStep(approvalProcessDto.getId(), currentStepId);

        workFlow.setApprovalProcessStepId(approvalProcessStepId);
        workFlow.setDealType(dealType);
        workFlow.setDealTime(new Timestamp(new Date().getTime()));
        workFlowService.save(workFlow);
    }
}
