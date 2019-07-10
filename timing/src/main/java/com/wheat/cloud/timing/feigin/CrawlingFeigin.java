package com.wheat.cloud.timing.feigin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="crwaling")
public interface CrawlingFeigin {

    @RequestMapping("/forecast/execute")
    public void executeForecast();

    @RequestMapping("/realTime/execute")
    public void executeRealTime();

    @RequestMapping("/hour24/execute")
    public void executeHour24();
}
