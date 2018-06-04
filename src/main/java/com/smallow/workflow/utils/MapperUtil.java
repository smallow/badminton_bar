package com.smallow.workflow.utils;

import com.smallow.workflow.entity.ApprovalStep;
import com.smallow.workflow.entity.PendingItem;
import com.smallow.workflow.entity.WorkFlow;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by wanghuidong on 2018/5/9.
 */
public class MapperUtil {

    public static final Map<String, String> workFlowMapper = new HashMap<String, String>() {{
        put("id", "id");
        put("work_id", "workId");
        put("user_id", "userId");
        put("deal_type", "dealType");
        put("deal_remark", "dealRemark");
        put("deal_time", "dealTime");
        put("approval_process_id", "approvalProcessId");
        put("approval_process_step_id", "approvalProcessStepId");
        put("pending_item_id", "pendingItemId");
        put("create_time", "createTime");
        put("update_time", "updateTime");
    }};

    public static final Map<String, String> pendingItemMapper = new HashMap<String, String>() {{
        put("id", "id");
        put("work_id", "workId");
        put("title", "title");
        put("business_code", "businessCode");
        put("user_id", "userId");
        put("status", "status");
        put("send_user_id", "sendUserId");
        put("send_time", "sendTime");
        put("approval_process_id", "approvalProcessId");
        put("approval_process_step_id", "approvalProcessStepId");
        put("create_time", "createTime");
        put("update_time", "updateTime");
    }};

    public static void main(String args[]) {
        WorkFlow workFlow = new WorkFlow();
        //PendingItem pendingItem = new PendingItem();
        //System.out.println(new MapperUtil().genResultMap(workFlow, workFlowMapper));
        //System.out.println(new MapperUtil().genBaseColumnList(workFlow, workFlowMapper));

        System.out.println(new MapperUtil().genSave(workFlow,"wk_work_flow_history",workFlowMapper));

    }


    public String createBaseMapper(Object object) {
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >");

        String className = object.getClass().getName();
        xml.append("<mapper namespace=\"" + className + "Mapper\">");

        return xml.toString();
    }


    private String genResultMap(Object object, Map<String, String> mapper) {
        String className = object.getClass().getName();
        StringBuilder str = new StringBuilder("<resultMap id=\"baseResultMap\" type=\"" + className + "\">");
        str.append("<id column=\"id\" property=\"id\" jdbcType=\"INTEGER\"/>");

        Field[] fields = object.getClass().getDeclaredFields();
        Arrays.stream(fields).filter(field -> !field.getName().equals("id")).forEach(field -> {
            str.append("<result column=\"" + getKeyByValue(mapper, field.getName()) + "\" property=\"" + field.getName() + "\" jdbcType=\"" + jdbcType.get(field.getType().getName()) + "\"/>");
        });
        str.append("</resultMap>");
        return str.toString();
    }

    private String genBaseColumnList(Object object, Map<String, String> mapper) {

        return "<sql id=\"Base_Column_List\">" + getColumnNames(object, mapper) + "</sql>";
    }


    private String genSave(Object object, String table, Map<String, String> mapper) {
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuilder str = new StringBuilder("INSERT INTO ").append(table).append("(").append(getColumnNames(object, mapper)).append(") VALUES (")
                .append(getPropertiesNames(object)).append(")");
        StringBuilder str2 = new StringBuilder("<insert id=\"save\" parameterType=\"" + object.getClass().getName() + "\">");
        str2.append(str).append("</insert>");
        return str2.toString();
    }


    private String getPropertiesNames(Object object) {
        StringBuilder str = new StringBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> str.append("#{" + field.getName() + "},"));
        String str2 = str.substring(0, str.lastIndexOf(","));
        return str2;
    }

    private String getColumnNames(Object object, Map<String, String> mapper) {
        StringBuilder str = new StringBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> str.append(getKeyByValue(mapper, field.getName()) + ","));
        String str2 = str.substring(0, str.lastIndexOf(","));
        return str2;
    }

    private void log(Object s) {
        System.out.println(s);
    }

    private String getKeyByValue(Map<String, String> workFlowMapper, String value) {
        String key = "";
        Optional<Map.Entry<String, String>> e = workFlowMapper.entrySet().stream().filter(entry -> entry.getValue().equals(value)).findFirst();
        key = e.orElse(null).getKey();
        //log("value:"+value+" key:"+key);
        return key;
    }

    private void getJdbcTypeByFiled(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            log(jdbcType.get(fields[i].getType().getName()));
        }
    }


    final public static Map<String, String> jdbcType = new HashMap<String, String>() {
        {
            put("java.lang.String", "VARCHAR");
            put("java.lang.Integer", "INTEGER");
            put("java.sql.Timestamp", "TIMESTAMP");
        }
    };


}
