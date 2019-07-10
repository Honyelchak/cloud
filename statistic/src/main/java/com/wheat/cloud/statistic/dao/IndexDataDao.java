package com.wheat.cloud.statistic.dao;

import com.wheat.cloud.statistic.entity.IndexData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndexDataDao extends JpaRepository<IndexData,String> {

//    @Query(value = "select * from forecast_result where DATEDIFF(forecastime,:date1) = 0;", nativeQuery = true)
//    public List getToday(@Param("date1") Date date1);


    @Query(value = "select * from data_sum ORDER BY update_time DESC LIMIT 1", nativeQuery = true)
    public List getToday();
}