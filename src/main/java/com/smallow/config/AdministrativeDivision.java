package com.smallow.config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghuidong on 2018/3/2.
 * 行政区划配置
 */

public class AdministrativeDivision {

    private static List<Map<String, Object>> province = new ArrayList<>();
    private static List<Map<String, Object>> city = new ArrayList<>();
    private static List<Map<String, Object>> area = new ArrayList<>();
    private static List<Map<String, Object>> arena = new ArrayList<>();

    public static void setProvince(List<Map<String, Object>> data) {
        province = data;
    }

    public static List<Map<String, Object>> getProvince() {
        return province;
    }


    public static void setCity(List<Map<String, Object>> data) {
        city = data;
    }

    public static List<Map<String, Object>> getCity() {
        return city;
    }

    public static void setArea(List<Map<String, Object>> data) {
        area = data;
    }

    public static List<Map<String, Object>> getArea() {
        return area;
    }

    public static void setArena(List<Map<String, Object>> data) {
        arena = data;
    }

    public static List<Map<String, Object>> getArena() {
        return arena;
    }

}
