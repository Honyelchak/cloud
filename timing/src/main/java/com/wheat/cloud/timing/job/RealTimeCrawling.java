package com.wheat.cloud.timing.job;

import com.wheat.cloud.timing.feigin.CrawlingFeigin;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

public class RealTimeCrawling implements Job {

    @Resource
    CrawlingFeigin crawlingFeigin;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        crawlingFeigin.executeRealTime();
    }
}
