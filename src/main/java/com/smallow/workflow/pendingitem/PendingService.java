package com.smallow.workflow.pendingitem;

/**
 * Created by wanghuidong on 2018/5/3.
 */
public interface PendingService {

    PendingItem create(Object workData, Integer workId, Integer userId, Integer status,String memo,String businessCode);

    PendingItem findByWorkId(Integer workId);
}
