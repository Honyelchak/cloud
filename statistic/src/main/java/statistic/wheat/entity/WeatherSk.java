package statistic.wheat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "weather_sk")
public class WeatherSk  implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 城市编号
     */
    @Column(name = "city_id")
    private String cityId;

    /**
     * 更新时间
     */

    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMdd")
    private Date updateTime;

    /**
     * 气温
     */
    private String temperature;

    @Column(name = "wind_speed")
    private String windSpeed;


    /**
     * 风力
     */
    @Column(name = "wind_power")
    private String windPower;

    /**
     * 风向英文缩写
     */
    @Column(name = "wind_direction_en")
    private String windDirectionEn;

    /**
     * 风向中文
     */
    @Column(name = "wind_direction")
    private String windDirection;

    /**
     * 湿度
     */
    private String humidity;

    private String weather;

    @Column(name = "weather_en")
    private String weatherEn;

    @Column(name = "weather_code")
    private String weatherCode;

    /**
     * 能见度
     */
    private String visibility;

    /**
     * 气压
     */
    @Column(name = "air_pressure")
    private String airPressure;

    /**
     * 24小时降雨量
     */
    private String rain24h;

    /**
     * 降雨量
     */
    private String rain;

    /**
     * pm2.5
     */
    @Column(name = "aqi_pm25")
    private String aqiPm25;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取城市编号
     *
     * @return city_id - 城市编号
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 设置城市编号
     *
     * @param cityId 城市编号
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取气温
     *
     * @return temperature - 气温
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * 设置气温
     *
     * @param temperature 气温
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /**
     * @return wind_speed
     */
    public String getWindSpeed() {
        return windSpeed;
    }

    /**
     * @param windSpeed
     */
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * 获取风力
     *
     * @return wind_power - 风力
     */
    public String getWindPower() {
        return windPower;
    }

    /**
     * 设置风力
     *
     * @param windPower 风力
     */
    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    /**
     * 获取风向英文缩写
     *
     * @return wind_direction_en - 风向英文缩写
     */
    public String getWindDirectionEn() {
        return windDirectionEn;
    }

    /**
     * 设置风向英文缩写
     *
     * @param windDirectionEn 风向英文缩写
     */
    public void setWindDirectionEn(String windDirectionEn) {
        this.windDirectionEn = windDirectionEn;
    }

    /**
     * 获取风向中文
     *
     * @return wind_direction - 风向中文
     */
    public String getWindDirection() {
        return windDirection;
    }

    /**
     * 设置风向中文
     *
     * @param windDirection 风向中文
     */
    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * 获取湿度
     *
     * @return humidity - 湿度
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * 设置湿度
     *
     * @param humidity 湿度
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    /**
     * @return weather
     */
    public String getWeather() {
        return weather;
    }

    /**
     * @param weather
     */
    public void setWeather(String weather) {
        this.weather = weather;
    }

    /**
     * @return weather_en
     */
    public String getWeatherEn() {
        return weatherEn;
    }

    /**
     * @param weatherEn
     */
    public void setWeatherEn(String weatherEn) {
        this.weatherEn = weatherEn;
    }

    /**
     * @return weather_code
     */
    public String getWeatherCode() {
        return weatherCode;
    }

    /**
     * @param weatherCode
     */
    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    /**
     * 获取能见度
     *
     * @return visibility - 能见度
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * 设置能见度
     *
     * @param visibility 能见度
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * 获取气压
     *
     * @return air_pressure - 气压
     */
    public String getAirPressure() {
        return airPressure;
    }

    /**
     * 设置气压
     *
     * @param airPressure 气压
     */
    public void setAirPressure(String airPressure) {
        this.airPressure = airPressure;
    }

    /**
     * 获取24小时降雨量
     *
     * @return rain24h - 24小时降雨量
     */
    public String getRain24h() {
        return rain24h;
    }

    /**
     * 设置24小时降雨量
     *
     * @param rain24h 24小时降雨量
     */
    public void setRain24h(String rain24h) {
        this.rain24h = rain24h;
    }

    /**
     * 获取降雨量
     *
     * @return rain - 降雨量
     */
    public String getRain() {
        return rain;
    }

    /**
     * 设置降雨量
     *
     * @param rain 降雨量
     */
    public void setRain(String rain) {
        this.rain = rain;
    }

    /**
     * 获取pm2.5
     *
     * @return aqi_pm25 - pm2.5
     */
    public String getAqiPm25() {
        return aqiPm25;
    }

    /**
     * 设置pm2.5
     *
     * @param aqiPm25 pm2.5
     */
    public void setAqiPm25(String aqiPm25) {
        this.aqiPm25 = aqiPm25;
    }
}