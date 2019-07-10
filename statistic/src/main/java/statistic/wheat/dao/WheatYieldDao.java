package statistic.wheat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import statistic.wheat.entity.WheatYield;


import java.util.List;

public interface WheatYieldDao extends JpaRepository<WheatYield,String> {
    //查询总产量
    @Query(value = "select yield from wheat_yield where city_name = :cityName", nativeQuery = true)
    public List findYield(@Param("cityName") String cityName);
    //查询亩均产量
    @Query(value = "select average_product from wheat_yield where city_name = :cityName", nativeQuery = true)
    public List findAverageProduct(@Param("cityName") String cityName);
    //查询种植面积
    @Query(value = "select area from wheat_yield where city_name = :cityName", nativeQuery = true)
    public List findArea(@Param("cityName") String cityName);
    //查询总产量增长率
    @Query(value = "select `yield_growth_rate` from wheat_yield where city_name = :cityName", nativeQuery = true)
    public List findYieldGrowthRate(@Param("cityName") String cityName);
    //查询亩均产量增长值
    @Query(value = "select `average_product_growth_rate` from wheat_yield where city_name = :cityName", nativeQuery = true)
    public List findaverageProductGrowthRate(@Param("cityName") String cityName);
    //查询种植面积增长值
    @Query(value = "select `area_growth_rate` from wheat_yield where city_name = :cityName", nativeQuery = true)
    public List findAreaGrowthRate(@Param("cityName") String cityName);

    @Query("select w from WeatherSk w ")
    public List<WheatYield> findAll();

}
