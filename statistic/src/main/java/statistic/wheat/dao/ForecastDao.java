package statistic.wheat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import statistic.wheat.entity.Forecast;
import statistic.wheat.entity.Weather24d;

import java.util.Date;
import java.util.List;

public interface ForecastDao extends JpaRepository<Forecast,String> {

    @Query(value = "select * from forecast_result where DATEDIFF(forecastime,:date1) = 0 and disaster_level != 0;", nativeQuery = true)
    public List getToday(@Param("date1") Date date1);


    /*@Query(value = "select f from Forecast as f where DATEDIFF(f.forecastime,'') = 0;")
    public List getToday1(Date date1);*/
}
