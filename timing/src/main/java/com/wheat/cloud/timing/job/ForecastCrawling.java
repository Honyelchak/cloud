package com.wheat.cloud.timing.job;

import com.wheat.cloud.timing.feigin.CrawlingFeigin;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@EnableScheduling
public class ForecastCrawling implements Job {

    @Resource
    CrawlingFeigin crawlingFeigin;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        crawlingFeigin.executeForecast();
    }
}
