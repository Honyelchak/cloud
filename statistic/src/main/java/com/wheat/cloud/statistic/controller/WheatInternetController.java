package com.wheat.cloud.statistic.controller;

import com.wheat.cloud.statistic.service.WheatInternetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import com.wheat.cloud.statistic.entity.WheatInternet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WheatInternetController {


    @Autowired
    private WheatInternetService wheatInternetService;
    @RequestMapping(value = "findinternetAll")
    public ModelAndView getAll()
    {
        List<WheatInternet> list = wheatInternetService.findAll();
        Map map=new HashMap();
        map.put("data",list);
        return new ModelAndView(new MappingJackson2JsonView(),map);
    }



    @RequestMapping(value = "wheatinternet")
    public ModelAndView getHourSoilTem(String cityid){
        List<Map<String, Object>> list=null;
        if(cityid!=null&&cityid!=""&&!cityid.equals(null)&&!cityid.equals(""))
        {
            list= wheatInternetService.getHourSoilTem(cityid);
        }
        list= wheatInternetService.getHourSoilTem("22239");

        Map map=new HashMap();
        map.put("data",list);

        return new ModelAndView(new MappingJackson2JsonView(),map);
    }
    /*
     *根据城市id获取本城市的最高温度，最低温度，实时平均温度
     *默认城市洛阳
     * list中三个值
     * 1.实时平均温度
     * 2.最高温度
     * 3.最低温度
     */
    @RequestMapping(value = "getRealAvgSoilTemperature")
    public ModelAndView getRealAvgTemperature(String cityid)
    {
        List<Double> list=new ArrayList<Double>();
        if(cityid!=null&&cityid!=""&&!cityid.equals(null)&&!cityid.equals("")) {
            list= wheatInternetService.getRealAvgTemperature(cityid);
        }

        else list=wheatInternetService.getRealAvgTemperature("22239");
        Map map=new HashMap();
        map.put("avgTem",list.get(0));
        map.put("maxTem",list.get(1));
        map.put("minTem",list.get(2));
        return new ModelAndView(new MappingJackson2JsonView(),map);
    }
}
