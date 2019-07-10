package com.wheat.cloud.statistic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wheat.cloud.statistic.dao.WeatherSKDao;
import com.wheat.cloud.statistic.entity.WeatherSk;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeathSkService {
    @Autowired
    WeatherSKDao weatherSKDao;

    public List<WeatherSk> findAll() {
        return weatherSKDao.findAll();
    }

    public List<Double> getRealAvgTemperature(String cityid) {

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");


        Date date=new Date();
        String nowdate=format.format(date);
        try {
            date =format.parse(nowdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List list=weatherSKDao.findAvgTemByNow(date,cityid);
        double avgTem=0,maxTem=0,minTem=0;
        if(list.get(0)!=null) {
             avgTem = Double.parseDouble(list.get(0).toString());
        }
        list=weatherSKDao.findMaxTemByNow(date,cityid);
        if(list.get(0)!=null) {
             maxTem = Double.parseDouble(list.get(0).toString());
        }

        list=weatherSKDao.findMinTemByNow(date,cityid);
        if(list.get(0)!=null) {
             minTem = Double.parseDouble(list.get(0).toString());
        }
        List<Double> Temlist=new ArrayList<Double>();
        Temlist.add(avgTem);
        Temlist.add(maxTem);
        Temlist.add(minTem);
        return  Temlist;
    }
        /*
        查找城市最新的湿度
         */
    public String findHumidity(String cityid){
        List list=weatherSKDao.findHumidity(cityid);
        return list.get(0).toString();

    }

    public List<Map<String, Object>> getTemList() {    //返回一个有序对象集合  在 controller中用到tem属性和cityid属性  返回前端
        //物联网最左侧
        //SELECT * FROM 表名 where time in (select max(time) from 表名)    选出最近一天的数据
        List all = weatherSKDao.findByUpdateTime();    //最后的结果为WeatherSks  all是一开始未经筛选的最近日期的数据
        return all;
    }

    public String[] getSkHum() {             // 最后的湿度结果  字符串形式返回前端
        List<WeatherSk> all;  //最后的结果为WeatherSks  all是一开始未经筛选的

        all = weatherSKDao.findByUpdateTime();


        int sum = 0, humSum = 0;

        for (WeatherSk a : all) {
            sum++;
            humSum += Integer.valueOf(a.getHumidity().substring(0, 2));
        }
        int compare=humSum/sum;
        String skHum[]=new String[2];

        skHum[0]=compare+"%";

        if (compare>80){
            skHum[1]="潮湿";
        }else if(compare<40){
            skHum[1]="干燥";
        }else{
            skHum[1]="适宜";
        }

        return  skHum;

    }


    public List getHourTem(String cityid) {
        return weatherSKDao.getHourTem(cityid);
    }

    public List<Map<String,Object>> findHuorHumidity(String cityid) {
        return  weatherSKDao.getHourHumidity(cityid);
    }

    public String findSoilHumidity(String cityid) {
        List list=weatherSKDao.findSoilHumidity(cityid);
        return list.get(0).toString();
    }

    public String getNameById(String cityid) {
        List list=weatherSKDao.getNameById(cityid);
        return list.get(0).toString();
    }
}
