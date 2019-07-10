package com.wheat.cloud.statistic.dao;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityMappingDao {
    @Query("SELECT w FROM CityMapping w where w.city_name in (select w.city_name from CityMapping  w)")
    public List<String> findAll();
}
