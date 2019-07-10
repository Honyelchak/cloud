package com.wheat.cloud.statistic.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import com.wheat.cloud.statistic.service.WheatYieldService;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("weatheryield")
public class WheatYieldController {

    @Resource
    WheatYieldService wheatYieldService;


    @RequestMapping("/data")
    @ResponseBody
    public ModelAndView findYield(@RequestBody String city) throws Exception {
        HashMap map = new HashMap();
        String cityName = URLDecoder.decode(city,"UTF-8");
        //sSystem.out.println(cityName.length());
        if (cityName.length()==4){
            cityName=cityName.substring(0,2);
        }
        if (cityName.length()==5){
            cityName=cityName.substring(0,3);
        }
        //System.out.println(cityName);
        List yield = wheatYieldService.findYield(cityName);
        //System.out.println(yield);
        List averageProduct = wheatYieldService.findAverageProduct(cityName);
        List area = wheatYieldService.findArea(cityName);
        List yieldGrowthRate = wheatYieldService.findYieldGrowthRate(cityName);
        List averageProductGrowthRate = wheatYieldService.findaverageProductGrowthRate(cityName);
        List areaGrowthRate = wheatYieldService.findAreaGrowthRate(cityName);

        //map.put("yield", cityName);
        map.put("yield", yield);
        map.put("averageProduct", averageProduct);
        map.put("area", area);
        map.put("yieldGrowthRate", yieldGrowthRate);
        map.put("averageProductGrowthRate", averageProductGrowthRate);
        map.put("areaGrowthRate", areaGrowthRate);

        return new ModelAndView(new MappingJackson2JsonView(), map);
   }




}
