package com.wheat.cloud.timing.controller;

import com.wheat.cloud.timing.feigin.CrawlingFeigin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 该类提供通过url启动爬虫的功能。
 *
 */

@RequestMapping("crawling")
@Controller
@EnableScheduling
public class WeatherController {

    @Resource
    CrawlingFeigin crawlingFeigin;

    @RequestMapping("/realTime")
    @Scheduled(cron="23 7 * * * ?")
    public void weather_sk(){
        crawlingFeigin.executeRealTime();
    }

    @RequestMapping("/forecast")
    @Scheduled(cron="50 10 9 * * ?")
    public void weather_forecast(){
        crawlingFeigin.executeForecast();
    }

    @RequestMapping("/hour24")
    @Scheduled(cron="34 19 9 * * ?")
    public void weather_hour24(){
        crawlingFeigin.executeHour24();
    }

    @RequestMapping("/test")
    //@Scheduled(cron="0 * * * * ?")
    public void test(){
            System.out.println("测试Quartz框架是否正常！");
    }
}
