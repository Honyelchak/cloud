package com.wheat.cloud.statistic.service;

import org.springframework.stereotype.Service;
import com.wheat.cloud.statistic.dao.Weather24dDao;
import com.wheat.cloud.statistic.entity.Weather24d;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;




@Service
public class Weather24dService {
    /**
     * 判断当前日期是星期几
     *
     * @param pTime 修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }




    @Resource
    Weather24dDao weather24dDao;

    public List<Weather24d> findAll() {
        return weather24dDao.findAll();
    }

    public List findOneWeeek(Date d, String  cityId ){
        return weather24dDao.findOneWeekTem(d, cityId);
    }
    public List findOneWeeek1(Date d, String cityId){
        return weather24dDao.findOneWeekTem1(d, cityId);
    }
    public List findOneWeeek2(Date d, String cityId){
        return weather24dDao.findOneWeekTem2(d, cityId);
    }



}
