package com.smallow.workflow.service.impl;

import com.smallow.workflow.entity.WorkFlow;
import com.smallow.workflow.mapper.WorkFLowMapper;
import com.smallow.workflow.service.WorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanghuidong on 2018/5/8.
 */
@Service
public class WorkFlowServiceImpl implements WorkFlowService {

    @Autowired
    private WorkFLowMapper workFLowMapper;

    @Override
    public void save(WorkFlow workFlow) {
        workFLowMapper.save(workFlow);
    }
}
