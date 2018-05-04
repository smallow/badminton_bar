package com.smallow.workflow.mapper;

import com.smallow.workflow.pendingitem.PendingItem;
import org.springframework.stereotype.Component;

/**
 * Created by wanghuidong on 2018/5/3.
 */
@Component(value = "pendingItemMapper")
public interface PendingItemMapper {

    Integer save(PendingItem pendingItem);

    PendingItem findByWorkId(Integer workId);
}
