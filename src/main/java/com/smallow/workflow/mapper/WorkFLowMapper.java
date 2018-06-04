package com.smallow.workflow.mapper;

import com.smallow.workflow.entity.WorkFlow;
import org.springframework.stereotype.Component;

/**
 * Created by wanghuidong on 2018/5/8.
 */
@Component(value = "workFlowMapper")
public interface WorkFLowMapper {

    void save(WorkFlow workFlow);
}
