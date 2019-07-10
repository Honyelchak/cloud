package statistic.wheat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import statistic.wheat.entity.WeatherSk;
import statistic.wheat.service.WeathSkService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WeatherSKController {
    @Autowired
    private WeathSkService skService;

    @RequestMapping(value = "test")
    public String test(){
        return "test";
    }


    @RequestMapping(value = "findAll")
    public ModelAndView getAll()
    {
        List<WeatherSk> list =skService.findAll();
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
    @RequestMapping(value = "getRealAvgTemperature")
    public ModelAndView getRealAvgTemperature(String cityid)
    {
        List<Double> list=new ArrayList<Double>();
        if(cityid!=null&&cityid!=""&&!cityid.equals(null)&&!cityid.equals("")) {
             list= skService.getRealAvgTemperature(cityid);
        }

        else list=skService.getRealAvgTemperature("101180401");
        Map map=new HashMap();
        map.put("avgTem",list.get(0));
        map.put("maxTem",list.get(1));
        map.put("minTem",list.get(2));
        return new ModelAndView(new MappingJackson2JsonView(),map);
    }


    @RequestMapping(value ="tem")

    public ModelAndView getTemList(){


        List<Map<String, Object>> list= skService.getTemList();
        Map map=new HashMap();
        map.put("data",list);
        return  new ModelAndView(new MappingJackson2JsonView(), map);
    }



    @RequestMapping(value = "hum")
    public String[] getHum(){
        return skService.getSkHum();
    }

    public String getHumidity(String cityid){
       String humidity="";

        if(cityid!=null&&cityid!=""&&!cityid.equals(null)&&!cityid.equals(""))
        {
            humidity= skService.findHumidity(cityid);
        }
        humidity= skService.findHumidity(cityid);


        return humidity;
    }

    /**
     *
     * @param cityid  城市ID用户获取空气湿度
     * @param locationid 站点ID 用于获取土壤湿度
     * @return
     */
    @RequestMapping(value = "gethumidity")
    public ModelAndView getHum(String cityid,String  locationid){
        //System.out.println("-------"+cityid);
        //System.out.println("-------"+locationid);
        String airhum=getHumidity(cityid);
        String soilhum=getSoilHumidity(locationid);
        Map map=new HashMap();
        System.out.println("-------"+airhum);
        System.out.println("-------"+soilhum);
        if (airhum==null){
            airhum="15";
        }
        if (soilhum==null){
            soilhum="16";
        }
        map.put("airhum",airhum);
        map.put("soilhum",soilhum);
        return  new ModelAndView(new MappingJackson2JsonView(), map);
    }


    public String getSoilHumidity(String cityid){
        String humidity="";

        if(cityid!=null&&cityid!=""&&!cityid.equals(null)&&!cityid.equals(""))
        {
            humidity= skService.findSoilHumidity(cityid);
        }
        humidity= skService.findSoilHumidity(cityid);


        return humidity;
    }

    @RequestMapping(value = "fivehourtem")
    @ResponseBody
    public ModelAndView getFiveHourTem(@RequestBody String cityid){
        cityid = cityid.substring(0,cityid.length()-1);
        //System.out.println(cityid);
        List<Map<String, Object>> list=null;
        List<Double> list2=null;
        if(cityid!=null&&cityid!=""&&!cityid.equals(null)&&!cityid.equals(""))
        {
            list= skService.getHourTem(cityid);
            list2 =skService.getRealAvgTemperature(cityid);
        }
        list= skService.getHourTem("101180401");
        list2 =skService.getRealAvgTemperature("101180401");
        Map map=new HashMap();
        map.put("data",list);
        if(list2!=null) {
            map.put("maxtem", list2.get(1));
            map.put("mintem", list2.get(2));
        }

        return new ModelAndView(new MappingJackson2JsonView(),map);
    }
    @RequestMapping(value = "hourtem")
    @ResponseBody
    public ModelAndView getHourTem(@RequestBody String cityid){
        cityid = cityid.substring(0,cityid.length()-1);
        List<Map<String, Object>> list=null;
        if(cityid!=null&&cityid!=""&&!cityid.equals(null)&&!cityid.equals(""))
        {
            list= skService.getHourTem(cityid);
        }
        list= skService.getHourTem(cityid);
        String cityname=skService.getNameById(cityid);
        Map map=new HashMap();
        map.put("data",list);
        map.put("cityname",cityname);
        return new ModelAndView(new MappingJackson2JsonView(),map);
    }
    @RequestMapping(value = "hourHum")
    @ResponseBody
    public ModelAndView getHourHum(@RequestBody String cityid){
        cityid = cityid.substring(0,cityid.length()-1);
        System.out.println("123123------"+cityid);
        List<Map<String, Object>> list=null;

        if(cityid!=null&&cityid!=""&&!cityid.equals(null)&&!cityid.equals(""))
        {
            list= skService.findHuorHumidity(cityid);
        }
        list= skService.findHuorHumidity(cityid);

        Map map=new HashMap();
        map.put("data",list);

        return new ModelAndView(new MappingJackson2JsonView(),map);
    }


}

