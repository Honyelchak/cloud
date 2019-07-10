package statistic.wheat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import statistic.wheat.entity.Weather24d;
import statistic.wheat.entity.WeatherSk;

import java.util.Date;
import java.util.List;

public interface Weather24dDao extends JpaRepository<Weather24d,String> {

    @Query(value = "select temperature from weather_24d where DATEDIFF(:d, time) < 9 and city_id = :cityId and :d>time ORDER BY time ASC", nativeQuery = true)
    public List findOneWeekTem(@Param("d") Date d, @Param("cityId") String cityId);

    @Query(value = "select time from weather_24d where DATEDIFF(:d, time) < 9 and city_id = :cityId and :d>time ORDER BY time ASC", nativeQuery = true)
    public List findOneWeekTem1(@Param("d") Date d, @Param("cityId") String cityId);

    @Query(value = "select rain from weather_24d where DATEDIFF(:d, time) < 9 and city_id = :cityId and :d>time ORDER BY time ASC", nativeQuery = true)
    public List findOneWeekTem2(@Param("d") Date d, @Param("cityId") String cityId);

    @Query("select w from WeatherSk w ")
    public List<Weather24d> findAll();

}
