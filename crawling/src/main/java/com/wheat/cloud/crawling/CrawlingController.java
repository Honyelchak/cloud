package com.wheat.cloud.crawling;

import com.wheat.cloud.crawling.weather.Hour24Crawling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CrawlingController {

    @RequestMapping("/test")
    @ResponseBody
    public ModelAndView test(){
        Map map = new HashMap();
        Hour24Crawling h = new Hour24Crawling();
        //h.execute();
        map.put("res", "success");
        map.put("code", 0);
        map.put("msg", "爬取完成");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

}
