package com.wheat.cloud.timing.controller;

import com.wheat.cloud.timing.entity.JobInfo;
import com.wheat.cloud.timing.feigin.CrawlingFeigin;
import com.wheat.cloud.timing.service.QuartzJobServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController {

    @RequestMapping("/")
    public String test(Map<String, Object> map){
        QuartzJobServiceImpl q = new QuartzJobServiceImpl();
        List<JobInfo> allInfo = q.getAllInfo();
        map.put("joblist", allInfo);
        return "index";
    }
}
