package statistic.wheat.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import statistic.wheat.dao.ForecastYieldDao;
import statistic.wheat.dao.IndexDataDao;
import statistic.wheat.entity.ForecastYield;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ForecastYieldService {

    @Autowired
    ForecastYieldDao forecastYieldDao;
    @Autowired
    private SessionFactory sessionFactory;


    public List<ForecastYield> getListByTime(String dateTime){
        List list=forecastYieldDao.getListByTime(dateTime);
        return list;

    }

    public void save(String date, String cityname, double yield,double area,double yieldMu, String cityid) {
        Session session=sessionFactory.openSession();
        Transaction ts=session.beginTransaction();
        session.createSQLQuery("insert into forecast_yield(city_id,city_name,yield,area,yield_per_mu,update_time) values('"+cityid+"','"+cityname+"',"+yield+","+area+","+ yieldMu +",'"+date+"')").executeUpdate();
        ts.commit();
        session.close();
    }

    public String getIdByName(String cityname) {
        return forecastYieldDao.getIdByName(cityname).get(0).toString();
    }

    public String getCityNameById(String id) {
        return forecastYieldDao.getCityNameById(id).get(0).toString();
    }
    public Double getAreaByCityId(String cityid) {
        return Double.parseDouble(forecastYieldDao.getAreaByCityId(cityid).get(0).toString());
    }
    public List getCountyByCityId(String cityid) {
        String s=cityid.substring(0,7);
        return forecastYieldDao.getCountyByCityId(s);
    }

    public Double getYieldByCityName(String name) {
        return Double.parseDouble(forecastYieldDao.getYieldByCityName(name).get(0).toString());

    }

    public Double getPerMuYieldByCityName(String name) {
        return Double.parseDouble(forecastYieldDao.getPerMuYieldByCityName(name).get(0).toString());

    }

    public List<Map<String,String>>  getYieldBy3dmap() {
        List list1=  forecastYieldDao.getYieldBy3dmap();
        List list2=  forecastYieldDao.getnameBy3dmap();
        List<Map<String,String>> list=new ArrayList<Map<String, String>>();
        for (int i=0;i<list1.size();i++)
        {
            Map<String,String> map= new HashMap<String, String>();
            map.put("name",list2.get(i).toString());
            map.put("value",list1.get(i).toString());
            list.add(map);
        }
        return  list;
    }

    /**
     * 修改主页的总产量显示
     * @param date
     */
    public void updateSumYield(String date) {
        double sumYield=Double.parseDouble(forecastYieldDao.getSumYield(date).get(0).toString());
        Session session=sessionFactory.openSession();
        Transaction ts=session.beginTransaction();
        session.createSQLQuery("UPDATE data_sum SET production_prediction = "+ sumYield+" WHERE id=1").executeUpdate();
        ts.commit();
        session.close();
    }
}
