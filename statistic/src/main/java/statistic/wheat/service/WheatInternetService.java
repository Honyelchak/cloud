package statistic.wheat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import statistic.wheat.dao.WheatInternetDao;
import statistic.wheat.entity.WheatInternet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WheatInternetService {
    @Autowired
    WheatInternetDao wheatInternetDao;

    public List<WheatInternet> findAll()
    {
        return wheatInternetDao.findAll();
    }

    public List<Map<String,Object>> getHourSoilTem(String s) {
        return wheatInternetDao.getHourSoilTem(s);
    }

    public List<Double> getRealAvgTemperature(String cityid) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");


        Date date=new Date();
        String nowdate=format.format(date);
        try {
            date =format.parse("2019-05-16");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List list=wheatInternetDao.findAvgTemByNow(date,cityid);
        double avgTem =Double.parseDouble(list.get(0).toString());
        list=wheatInternetDao.findMaxTemByNow(date,cityid);
        double maxTem =Double.parseDouble(list.get(0).toString());
        list=wheatInternetDao.findMinTemByNow(date,cityid);
        double minTem =Double.parseDouble(list.get(0).toString());
        List<Double> Temlist=new ArrayList<Double>();
        Temlist.add(avgTem);
        Temlist.add(maxTem);
        Temlist.add(minTem);
        return  Temlist;
    }
}
