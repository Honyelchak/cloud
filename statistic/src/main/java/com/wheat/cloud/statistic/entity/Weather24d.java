package com.wheat.cloud.statistic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "weather_24d")
public class Weather24d  implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "city_id")
    private String cityId;

    @Column(name = "time")
    private Date time;

    @Column(name = "temperature")
    private String temperature;

    @Column(name = "wind_power")
    private String windPower;

    @Column(name = "wind_direction")
    private String windDirection;


    @Column(name = "rain")
    private String rain;

    @Column(name = "humdity")
    private String humdity;

    @Column(name = "aqi_pm25")
    private String aqiPm25;

    @Column(name = "other")
    private String other;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getHumdity() {
        return humdity;
    }

    public void setHumdity(String humdity) {
        this.humdity = humdity;
    }

    public String getAqiPm25() {
        return aqiPm25;
    }

    public void setAqiPm25(String aqiPm25) {
        this.aqiPm25 = aqiPm25;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

}
