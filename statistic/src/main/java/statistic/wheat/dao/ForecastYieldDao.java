package statistic.wheat.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import statistic.wheat.entity.ForecastYield;
import statistic.wheat.entity.IndexData;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface ForecastYieldDao extends JpaRepository<IndexData,String> {

//    @Query(value = "select * from forecast_result where DATEDIFF(forecastime,:date1) = 0;", nativeQuery = true)
//    public List getToday(@Param("date1") Date date1);


    @Query(value = "select * from forecast_yield where update_time like '%:update_time+%'", nativeQuery = true)
    public List<ForecastYield> getListByTime(@Param("update_time") String update_time);
    @Query(value = "insert into  forecast_yield(city_id,city_name,yield,area,update_time) values(:cityid,:cityname,:yield,:area,:updatetime) ", nativeQuery = true)
    void save(@Param("updatetime") String updatetime,@Param("cityname")String cityname, @Param("yield")double yield,@Param("area")double area,@Param("cityid")String cityid );
    @Query(value = "select  city_id from  city_mapping where  city_name=:cityname", nativeQuery = true)
    List getIdByName(@Param("cityname") String cityname);
    @Query(value = "select  plant_area from  city_mapping where  city_id=:cityid", nativeQuery = true)
    List getAreaByCityId(@Param("cityid") String cityid);
    @Query(value = "select  city_id from  city_mapping where  city_id like concat(:s,'%') and city_id != concat(:s,'01')", nativeQuery = true)
    List getCountyByCityId(@Param("s")String s);
    @Query(value = "select yield from forecast_yield where city_name = :name", nativeQuery = true)
    List getYieldByCityName(@Param("name") String name);
    @Query(value = "select  city_name from  city_mapping where  city_id = :id limit 1", nativeQuery = true)
    List getCityNameById(@Param("id") String id);
    @Query(value = "select yield_per_mu from forecast_yield where city_name = :name", nativeQuery = true)
    List getPerMuYieldByCityName(@Param("name") String name);
    @Query(value = "select city_name  from forecast_yield", nativeQuery = true)
    List  getnameBy3dmap();
    @Query(value = "select  yield   from forecast_yield", nativeQuery = true)
    List  getYieldBy3dmap();
    @Query(value = "select sum(yield) from forecast_yield where right(city_id,2) = '01' where update_time=:date", nativeQuery = true)
    List getSumYield(@Param("date") String date);


}