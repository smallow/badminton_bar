package com.smallow.workflow.service;

import com.smallow.workflow.entity.PendingItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Created by wanghuidong on 2018/5/3.
 */
public interface PendingService {


    void save(PendingItem pendingItem);

    Page<PendingItem> findList(Map<String, Object> param, Pageable pageable);
}
