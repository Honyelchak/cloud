package com.wheat.cloud.statistic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import com.wheat.cloud.statistic.service.IndexDataService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("IndexData")
public class IndexDataController {

    @Resource
    IndexDataService indexDataService;

    @RequestMapping(value = "indexData")
    public ModelAndView getHourHum(){
        List<Map<String, Object>> list= null;
        list= indexDataService.findIndexData();
        Map map=new HashMap();
        map.put("data",list);
        return new ModelAndView(new MappingJackson2JsonView(),map);
    }
}
