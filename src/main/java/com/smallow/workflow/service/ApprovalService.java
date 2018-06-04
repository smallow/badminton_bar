package com.smallow.workflow.service;

import com.smallow.workflow.dto.ApprovalProcessDto;
import com.smallow.workflow.entity.ApprovalProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by wanghuidong on 2018/4/27.
 */
public interface ApprovalService {


    /**
     * 查询单个流程
     */
    ApprovalProcessDto findOne(Integer id);

    /**
     * 查询业务代码查询相关流程
     */
    ApprovalProcessDto findByBusinessCode(String businessCode);

    //ApprovalProces
}
