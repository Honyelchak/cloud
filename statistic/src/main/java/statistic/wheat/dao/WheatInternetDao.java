package statistic.wheat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import statistic.wheat.entity.WheatInternet;


import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WheatInternetDao extends JpaRepository<WheatInternet,Integer> {
    /**
     * 查询所有
     * @return
     */
    @Query("select w from WheatInternet w ")
    public List<WheatInternet> findAll();
    /**
     * 查询当天平均温度
     * @return
     */
    @Query("select w from WheatInternet w ")

    public double findNowAvgTem();
@Query(value = "select (soil_humidity1+soil_humidity2+soil_humidity3+soil_humidity4)/4 as sum ,create_date FROM wheat_internet where location_id = :s GROUP BY create_date DESC limit 8", nativeQuery = true)
List getHourSoilTem(@Param("s") String s);
    @Query(value = "select AVG((soil_temperature1+soil_temperature2+soil_temperature3+soil_temperature4)/4) from wheat_internet AS w  where w.create_date >= :nowdate and w.location_id= :cityid", nativeQuery = true)
    List findAvgTemByNow(@Param("nowdate") Date nowdate,@Param("cityid") String cityid);
    @Query(value = "select MAX((soil_temperature1+soil_temperature2+soil_temperature3+soil_temperature4)/4) from wheat_internet AS w  where w.create_date >= :nowdate and w.location_id= :cityid", nativeQuery = true)
    List findMaxTemByNow(@Param("nowdate") Date nowdate,@Param("cityid") String cityid);
    @Query(value = "select min((soil_temperature1+soil_temperature2+soil_temperature3+soil_temperature4)/4) from wheat_internet AS w  where w.create_date >= :nowdate and w.location_id= :cityid", nativeQuery = true)
    List findMinTemByNow(@Param("nowdate") Date nowdate,@Param("cityid") String cityid);
}
