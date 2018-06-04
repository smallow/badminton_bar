package com.smallow.workflow.mapper;

import com.smallow.workflow.entity.PendingItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghuidong on 2018/5/3.
 */
@Component(value = "pendingItemMapper")
public interface PendingItemMapper {

    void save(PendingItem pendingItem);

    List<PendingItem> findList(Map<String, Object> param);

    long count(Map<String, Object> map);
}
