package statistic.wheat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import statistic.wheat.entity.IndexData;


import java.util.List;

public interface IndexDataDao extends JpaRepository<IndexData,String> {

//    @Query(value = "select * from forecast_result where DATEDIFF(forecastime,:date1) = 0;", nativeQuery = true)
//    public List getToday(@Param("date1") Date date1);


    @Query(value = "select * from data_sum ORDER BY update_time DESC LIMIT 1", nativeQuery = true)
    public List getToday();
}