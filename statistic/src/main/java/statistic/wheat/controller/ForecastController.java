package statistic.wheat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import statistic.wheat.service.ForecastService;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("forecast")
public class ForecastController {

    @Resource
    ForecastService forecastService;

    @RequestMapping("/getToday")
    public ModelAndView getToday(String forecasTime){
        HashMap map = new HashMap();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf.parse(forecasTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List list = forecastService.getToday(d);
        map.put("data", list);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @RequestMapping("/getYield")
    public ModelAndView getYield(){
        HashMap map = new HashMap();
        String result = forecastService.getYield();
        map.put("data", result);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }
}
