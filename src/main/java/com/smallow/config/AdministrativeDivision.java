package com.smallow.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghuidong on 2018/3/2.
 * 行政区划配置
 */
public class AdministrativeDivision {
    public static final Integer PROVINCE=1;
    public static final Integer CITY=2;
    public static final Integer AREA=3;

    private static List<Map<String, Object>> area = new ArrayList<>();
    private static List<Map<String, Object>> arena = new ArrayList<>();

    public static void setArea(List<Map<String, Object>> area) {
        AdministrativeDivision.area = area;
    }

    public static void setArena(List<Map<String, Object>> arena) {
        AdministrativeDivision.arena = arena;
    }

    public static List<Map<String, Object>> getArea() {
        return area;
    }

    public static List<Map<String, Object>> getArena() {
        return arena;
    }
}
