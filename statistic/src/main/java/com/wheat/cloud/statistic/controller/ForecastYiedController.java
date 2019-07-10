package com.wheat.cloud.statistic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import com.wheat.cloud.statistic.entity.ForecastYield;
import com.wheat.cloud.statistic.service.ForecastYieldService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("IndexData")
public class ForecastYiedController {
    @Autowired
    ForecastYieldService forecastYieldService;

    @RequestMapping(value = "getListByTime")
    public ModelAndView getListByTime(String date){

        List<ForecastYield> list=null;


        list= forecastYieldService.getListByTime(date);
        Map map=new HashMap();
        map.put("data",list);

        return new ModelAndView(new MappingJackson2JsonView(),map);
    }
    @RequestMapping(value = "getYieldBy3dmap")
    public ModelAndView getYieldBy3dmap(){

        List<Map<String,String>> list=null;


        list= forecastYieldService.getYieldBy3dmap();
        Map map=new HashMap();
        map.put("data",list);

        return new ModelAndView(new MappingJackson2JsonView(),map);
    }

    @RequestMapping(value = "getYieldByCity")
    public ModelAndView getYieldByCity(){
        String location[] ={"郑州"
                ,"安阳"
                ,"新乡"
                ,"许昌"
                ,"平顶山"
                ,"信阳"
                ,"南阳"
                ,"开封"
                ,"洛阳"
                ,"商丘"
                ,"周口"
                ,"驻马店"
                ,"三门峡"
        };
        Map map=new HashMap();
        for(String name:location)
        {
            Double yield=forecastYieldService.getYieldByCityName(name);
            map.put(name,yield);
        }
        return new ModelAndView(new MappingJackson2JsonView(),map);
    }
    @RequestMapping(value = "save")
    public ModelAndView save(String date,String cityname,String yield,String area){
        String cityid = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        date = sdf.format(new Date());
        String  s = "{\"data\":[{\"name\":\"三门峡\",\"yield\":\"43.96096\",\"area\":\"77.19\",\"yieldMu\":\"387.42605\"},{\"name\":\"信阳\",\"yield\":\"139.06359\",\"area\":\"306.56\",\"yieldMu\":\"308.58911\"},{\"name\":\"南阳\",\"yield\":\"400.38382\",\"area\":\"709.02\",\"yieldMu\":\"384.14987\"},{\"name\":\"商丘\",\"yield\":\"320.54383\",\"area\":\"588.87\",\"yieldMu\":\"370.29740\"},{\"name\":\"固始\",\"yield\":\"18.15845\",\"area\":\"37.0\",\"yieldMu\":\"333.85632\"},{\"name\":\"孟津\",\"yield\":\"14.91588\",\"area\":\"27.07\",\"yieldMu\":\"374.83767\"},{\"name\":\"安阳\",\"yield\":\"195.07654\",\"area\":\"320.38\",\"yieldMu\":\"414.21165\"},{\"name\":\"宝丰\",\"yield\":\"15.73624\",\"area\":\"26.02\",\"yieldMu\":\"411.41137\"},{\"name\":\"开封\",\"yield\":\"195.85650\",\"area\":\"299.92\",\"yieldMu\":\"444.23751\"},{\"name\":\"新乡\",\"yield\":\"212.09607\",\"area\":\"379.41\",\"yieldMu\":\"380.28265\"},{\"name\":\"桐柏\",\"yield\":\"8.31631\",\"area\":\"16.33\",\"yieldMu\":\"346.43925\"},{\"name\":\"永城\",\"yield\":\"56.47409\",\"area\":\"109.39\",\"yieldMu\":\"351.19982\"},{\"name\":\"西华\",\"yield\":\"34.56460\",\"area\":\"72.99\",\"yieldMu\":\"322.14462\"},{\"name\":\"西峡\",\"yield\":\"4.95550\",\"area\":\"10.8\",\"yieldMu\":\"312.13809\"},{\"name\":\"许昌\",\"yield\":\"131.39844\",\"area\":\"228.96\",\"yieldMu\":\"390.40311\"},{\"name\":\"郑州\",\"yield\":\"96.93133\",\"area\":\"163.06\",\"yieldMu\":\"404.38907\"},{\"name\":\"驻马店\",\"yield\":\"482.28152\",\"area\":\"750.52\",\"yieldMu\":\"437.14047\"}]\n}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int i = 0; i < jsonArray.size(); i++) {
            ForecastYield yieldObj = new ForecastYield();
            JSONObject jsonArray1 = jsonArray.getJSONObject(i);
            yieldObj.setArea(Double.parseDouble(jsonArray1.getString("area")));
            yieldObj.setYield(Double.parseDouble(jsonArray1.getString("yield")));
            yieldObj.setYieldPerMu(Double.parseDouble(jsonArray1.getString("yieldMu")));
            yieldObj.setCityname(jsonArray1.getString("name"));
            String cityId = forecastYieldService.getIdByName(yieldObj.getCityname());
            if(cityId.charAt(7) == '0' && cityId.charAt(8) == '1'){
                forecastYieldService.save(date, yieldObj.getCityname(), yieldObj.getYield(), yieldObj.getArea(), yieldObj.getYieldPerMu(), cityId);
                List list = forecastYieldService.getCountyByCityId(cityId);
                for (Object m : list){
                    String cityName = forecastYieldService.getCityNameById(m.toString());
                    if("固始".equals(cityName) || "桐柏".equals(cityName) || "西峡".equals(cityName) || "永城".equals(cityName) || "鸡公山".equals(cityName)){
                        continue;
                    }
                    Double area_county = forecastYieldService.getAreaByCityId(m.toString());
                    double yield_county = (area_county/yieldObj.getArea()) * yieldObj.getYield();
                    double mu = yield_county*10000/(area_county*15);
                    forecastYieldService.save(date, cityName, yield_county, area_county, mu, m.toString());
                }
            } else {
                if("宝丰".equals(yieldObj.getCityname()) || "孟津".equals(yieldObj.getCityname()) || "西华".equals(yieldObj.getCityname())){
                    String city = cityId.substring(0,7)+"01";
                    forecastYieldService.save(date, yieldObj.getCityname(), yieldObj.getYield(), yieldObj.getArea(), yieldObj.getYieldPerMu(), cityId);
                    double area_city = forecastYieldService.getAreaByCityId(city);
                    String cityName_search = forecastYieldService.getCityNameById(city);
                    double yield_city = (area_city/yieldObj.getArea())*yieldObj.getYield();
                    forecastYieldService.save(date, cityName_search, yield_city, area_city, yield_city*10000/(area_city*15), city);
                    List list = forecastYieldService.getCountyByCityId(city);
                    for (Object m : list){
                        if(m.toString().equals(cityId))continue;
                        String cityName = forecastYieldService.getCityNameById(m.toString());
                        Double area_county = forecastYieldService.getAreaByCityId(m.toString());
                        double yield_county = area_county/area_city * yield_city;
                        forecastYieldService.save(date, cityName, yield_county, area_county, yield_county*10000/(15*area_county), m.toString());
                    }
                } else {
                    forecastYieldService.save(date, yieldObj.getCityname(), yieldObj.getYield(), yieldObj.getArea(), yieldObj.getYieldPerMu(), cityId);
                }
            }
            //forecastYieldService.save(date, cityname, yield, area, cityid);
        }
        saveOther(date, "焦作", "新乡");
        saveOther(date, "济源", "洛阳");
        saveOther(date, "鹤壁", "新乡");
        saveOther(date, "濮阳", "安阳");
        saveOther(date, "漯河", "驻马店");
        forecastYieldService.updateSumYield(date);
        Map map=new HashMap();
        map.put("message","success");

        return new ModelAndView(new MappingJackson2JsonView(),map);
    }

    //焦作、鹤壁、濮阳、漯河、济源
    // city1 是未知的
    private void saveOther(String date, String city1, String city2){
        // 焦作
        String cityId = forecastYieldService.getIdByName(city1);
        Double areaCity = forecastYieldService.getAreaByCityId(cityId);
        Double perMuYield = forecastYieldService.getPerMuYieldByCityName(city2);
        double yieldCity = perMuYield * areaCity * 15 * 1000 * 0.98 / 1000 / 10000;
        forecastYieldService.save(date, city1, yieldCity, areaCity, perMuYield, cityId);
        List list = forecastYieldService.getCountyByCityId(cityId);
        for (Object m : list){
            String cityName = forecastYieldService.getCityNameById(m.toString());
            Double area_county = forecastYieldService.getAreaByCityId(m.toString());
            double yield_county = (area_county/areaCity) * yieldCity;
            double mu = yield_county*10000/(area_county*15);
            forecastYieldService.save(date, cityName, yield_county, area_county, mu, m.toString());
        }
    }
}
