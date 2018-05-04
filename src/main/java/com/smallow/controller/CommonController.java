package com.smallow.controller;

import com.smallow.VO.ResultVO;
import com.smallow.config.AdministrativeDivision;
import com.smallow.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by wanghuidong on 2018/3/23.
 */
@Controller
@RequestMapping("/common")
@Slf4j
public class CommonController {


    @GetMapping("/findAreaByPCode")
    @ResponseBody
    public List<Map<String, Object>> findAdministrativeDivisionByPCode(@RequestParam("pcode") String pcode) {
        List<Map<String, Object>> list = new ArrayList<>();
        list = AdministrativeDivision.getArea();
        List<Map<String, Object>> returnList = new ArrayList<>();
//        list.forEach(stringObjectMap -> {
//            if (((String) stringObjectMap.get("parent")).equals(pcode)) {
//                returnList.add(stringObjectMap);
//            }
//        });
        returnList=list.stream().filter(e -> ((String)e.get("parent")).equals(pcode)).collect(toList());
        return returnList;
    }
}
