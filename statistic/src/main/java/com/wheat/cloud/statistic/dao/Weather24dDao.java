package com.wheat.cloud.statistic.dao;

import com.wheat.cloud.statistic.entity.Weather24d;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface Weather24dDao extends JpaRepository<Weather24d,String> {

    @Query(value = "select temperature from weather_sk where DATEDIFF(:d, update_time) < 7 and city_id = :cityId and :d>update_time ORDER BY update_time ASC", nativeQuery = true)
    public List findOneWeekTem(@Param("d") Date d, @Param("cityId") String cityId);

    @Query(value = "select update_time from weather_sk where DATEDIFF(:d, update_time) < 7 and city_id = :cityId and :d>update_time ORDER BY update_time ASC", nativeQuery = true)
    public List findOneWeekTem1(@Param("d") Date d, @Param("cityId") String cityId);

    @Query(value = "select rain from weather_sk where DATEDIFF(:d, update_time) < 7 and city_id = :cityId and :d>update_time ORDER BY update_time ASC", nativeQuery = true)
    public List findOneWeekTem2(@Param("d") Date d, @Param("cityId") String cityId);

    @Query("select w from WeatherSk w ")
    public List<Weather24d> findAll();

}
