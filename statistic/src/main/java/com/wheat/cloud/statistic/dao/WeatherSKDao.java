package com.wheat.cloud.statistic.dao;

import com.wheat.cloud.statistic.entity.WeatherSk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;


public interface WeatherSKDao extends JpaRepository<WeatherSk,String> {
    /**
     * 查询所有
     * @return
     */
    @Query("select w from WeatherSk w ")
    public List<WeatherSk> findAll();

    @Query(value = "SELECT w.temperature, c.city_name FROM weather_sk AS w, city_mapping AS c WHERE w.update_time IN ( SELECT max(w.update_time) FROM weather_sk w) AND w.city_id = c.city_id and c.city_id LIKE '%01' ORDER BY w.temperature DESC", nativeQuery = true)
    List  findByUpdateTime();

//    @Query("select w from WeatherSk w ")

    /**
     * 查询今天平均气温
     * @return
     */
    @Query(value = "select AVG(w.temperature) from weather_sk AS w  where w.update_time >= :nowdate and w.city_id= :cityid", nativeQuery = true)
    List findAvgTemByNow(@Param("nowdate") Date nowdate, @Param("cityid") String cityid);
    /**
     * 查询今天最高温度
     * @return
     */
    @Query(value = "select MAX(w.temperature) from weather_sk AS w  where w.update_time >= :nowdate and w.city_id= :cityid", nativeQuery = true)
    List findMaxTemByNow(@Param("nowdate") Date nowdate, @Param("cityid") String cityid);
    /**
     * 查询今天最低温度
     * @return
     */
    @Query(value = "select MIN(w.temperature) from weather_sk AS w  where w.update_time >= :nowdate and w.city_id= :cityid", nativeQuery = true)
    List findMinTemByNow(@Param("nowdate") Date nowdate, @Param("cityid") String cityid);
//    SELECT humidity FROM weather_sk where city_id=101180901 GROUP BY update_time DESC LIMIT 1;
@Query(value = "SELECT humidity FROM weather_sk where city_id= :cityid GROUP BY update_time DESC LIMIT 1", nativeQuery = true)
List findHumidity(@Param("cityid") String cityid);
    @Query(value = "SELECT temperature,update_time FROM weather_sk where city_id= :cityid ORDER BY update_time DESC LIMIT 8", nativeQuery = true)
    List getHourTem(@Param("cityid") String cityid);
    @Query(value = "SELECT (soil_temperature1+soil_temperature2)/2 as  avgTem,create_date FROM wheat_internet where location_id= :cityid ORDER BY create_date DESC LIMIT 48", nativeQuery = true)
    List getHourHumidity(@Param("cityid") String cityid);
    @Query(value = "SELECT (soil_humidity1+soil_humidity2)/2 as  avgHum FROM wheat_internet where location_id= :cityid ORDER BY create_date DESC LIMIT 1", nativeQuery = true)
    List findSoilHumidity(@Param("cityid") String cityid);
    @Query(value = "select  city_name from city_mapping where city_id=:cityid", nativeQuery = true)
    List getNameById(@Param("cityid") String cityid);
}
