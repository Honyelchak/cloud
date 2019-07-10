package com.wheat.cloud.statistic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "forecast_result")
public class Forecast implements Serializable {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "city_id")
    private String cityId;


    @Column(name = "disaster_type")
    private int disasterType;

    @Column(name = "disaster_level")
    private int disasterLevel;

    @Column(name = "forecastime")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date forecasTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public int getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(int disasterType) {
        this.disasterType = disasterType;
    }

    public int getDisasterLevel() {
        return disasterLevel;
    }

    public void setDisasterLevel(int disasterLevel) {
        this.disasterLevel = disasterLevel;
    }

    public Date getForecasTime() {
        return forecasTime;
    }

    public void setForecasTime(Date forecasTime) {
        this.forecasTime = forecasTime;
    }
}
