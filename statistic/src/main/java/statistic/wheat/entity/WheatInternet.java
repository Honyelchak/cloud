package statistic.wheat.entity;

import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "wheat_internet")
public class WheatInternet {
    @Id
    @Column(name = "data_id")
    private Integer dataId;

    @Column(name = "location_id")
    private Integer locationId;

    /**
     * 更新时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 空气温度1
     */
    @Column(name = "air_temperature1")
    private String airTemperature1;

    /**
     * 空气温度2
     */
    @Column(name = "air_temperature2")
    private String airTemperature2;

    /**
     * 土壤温度1
     */
    @Column(name = "soil_temperature1")
    private String soilTemperature1;

    /**
     * 土壤温度2
     */
    @Column(name = "soil_temperature2")
    private String soilTemperature2;

    /**
     * 土壤温度3
     */
    @Column(name = "soil_temperature3")
    private String soilTemperature3;

    /**
     * 土壤温度4
     */
    @Column(name = "soil_temperature4")
    private String soilTemperature4;

    /**
     * 空气湿度1
     */
    @Column(name = "air_humidity1")
    private String airHumidity1;

    /**
     * 空气湿度2
     */
    @Column(name = "air_humidity2")
    private String airHumidity2;

    /**
     * 土壤湿度1
     */
    @Column(name = "soil_humidity1")
    private String soilHumidity1;

    /**
     * 土壤湿度2
     */
    @Column(name = "soil_humidity2")
    private String soilHumidity2;

    /**
     * 土壤湿度3
     */
    @Column(name = "soil_humidity3")
    private String soilHumidity3;

    /**
     * 土壤湿度4
     */
    @Column(name = "soil_humidity4")
    private String soilHumidity4;

    /**
     * 辐射
     */
    private String radiation;

    /**
     * 风速
     */
    private String canemometer;

    /**
     * 风向
     */
    private String dogvane;

    /**
     * 降雨量
     */
    private String rainfall;

    /**
     * @return data_id
     */
    public Integer getDataId() {
        return dataId;
    }

    /**
     * @param dataId
     */
    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    /**
     * @return location_id
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * @param locationId
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * 获取更新时间
     *
     * @return create_date - 更新时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置更新时间
     *
     * @param createDate 更新时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取空气温度1
     *
     * @return air_temperature1 - 空气温度1
     */
    public String getAirTemperature1() {
        return airTemperature1;
    }

    /**
     * 设置空气温度1
     *
     * @param airTemperature1 空气温度1
     */
    public void setAirTemperature1(String airTemperature1) {
        this.airTemperature1 = airTemperature1;
    }

    /**
     * 获取空气温度2
     *
     * @return air_temperature2 - 空气温度2
     */
    public String getAirTemperature2() {
        return airTemperature2;
    }

    /**
     * 设置空气温度2
     *
     * @param airTemperature2 空气温度2
     */
    public void setAirTemperature2(String airTemperature2) {
        this.airTemperature2 = airTemperature2;
    }

    /**
     * 获取土壤温度1
     *
     * @return soil_temperature1 - 土壤温度1
     */
    public String getSoilTemperature1() {
        return soilTemperature1;
    }

    /**
     * 设置土壤温度1
     *
     * @param soilTemperature1 土壤温度1
     */
    public void setSoilTemperature1(String soilTemperature1) {
        this.soilTemperature1 = soilTemperature1;
    }

    /**
     * 获取土壤温度2
     *
     * @return soil_temperature2 - 土壤温度2
     */
    public String getSoilTemperature2() {
        return soilTemperature2;
    }

    /**
     * 设置土壤温度2
     *
     * @param soilTemperature2 土壤温度2
     */
    public void setSoilTemperature2(String soilTemperature2) {
        this.soilTemperature2 = soilTemperature2;
    }

    /**
     * 获取土壤温度3
     *
     * @return soil_temperature3 - 土壤温度3
     */
    public String getSoilTemperature3() {
        return soilTemperature3;
    }

    /**
     * 设置土壤温度3
     *
     * @param soilTemperature3 土壤温度3
     */
    public void setSoilTemperature3(String soilTemperature3) {
        this.soilTemperature3 = soilTemperature3;
    }

    /**
     * 获取土壤温度4
     *
     * @return soil_temperature4 - 土壤温度4
     */
    public String getSoilTemperature4() {
        return soilTemperature4;
    }

    /**
     * 设置土壤温度4
     *
     * @param soilTemperature4 土壤温度4
     */
    public void setSoilTemperature4(String soilTemperature4) {
        this.soilTemperature4 = soilTemperature4;
    }

    /**
     * 获取空气湿度1
     *
     * @return air_humidity1 - 空气湿度1
     */
    public String getAirHumidity1() {
        return airHumidity1;
    }

    /**
     * 设置空气湿度1
     *
     * @param airHumidity1 空气湿度1
     */
    public void setAirHumidity1(String airHumidity1) {
        this.airHumidity1 = airHumidity1;
    }

    /**
     * 获取空气湿度2
     *
     * @return air_humidity2 - 空气湿度2
     */
    public String getAirHumidity2() {
        return airHumidity2;
    }

    /**
     * 设置空气湿度2
     *
     * @param airHumidity2 空气湿度2
     */
    public void setAirHumidity2(String airHumidity2) {
        this.airHumidity2 = airHumidity2;
    }

    /**
     * 获取土壤湿度1
     *
     * @return soil_humidity1 - 土壤湿度1
     */
    public String getSoilHumidity1() {
        return soilHumidity1;
    }

    /**
     * 设置土壤湿度1
     *
     * @param soilHumidity1 土壤湿度1
     */
    public void setSoilHumidity1(String soilHumidity1) {
        this.soilHumidity1 = soilHumidity1;
    }

    /**
     * 获取土壤湿度2
     *
     * @return soil_humidity2 - 土壤湿度2
     */
    public String getSoilHumidity2() {
        return soilHumidity2;
    }

    /**
     * 设置土壤湿度2
     *
     * @param soilHumidity2 土壤湿度2
     */
    public void setSoilHumidity2(String soilHumidity2) {
        this.soilHumidity2 = soilHumidity2;
    }

    /**
     * 获取土壤湿度3
     *
     * @return soil_humidity3 - 土壤湿度3
     */
    public String getSoilHumidity3() {
        return soilHumidity3;
    }

    /**
     * 设置土壤湿度3
     *
     * @param soilHumidity3 土壤湿度3
     */
    public void setSoilHumidity3(String soilHumidity3) {
        this.soilHumidity3 = soilHumidity3;
    }

    /**
     * 获取土壤湿度4
     *
     * @return soil_humidity4 - 土壤湿度4
     */
    public String getSoilHumidity4() {
        return soilHumidity4;
    }

    /**
     * 设置土壤湿度4
     *
     * @param soilHumidity4 土壤湿度4
     */
    public void setSoilHumidity4(String soilHumidity4) {
        this.soilHumidity4 = soilHumidity4;
    }

    /**
     * 获取辐射
     *
     * @return radiation - 辐射
     */
    public String getRadiation() {
        return radiation;
    }

    /**
     * 设置辐射
     *
     * @param radiation 辐射
     */
    public void setRadiation(String radiation) {
        this.radiation = radiation;
    }

    /**
     * 获取风速
     *
     * @return canemometer - 风速
     */
    public String getCanemometer() {
        return canemometer;
    }

    /**
     * 设置风速
     *
     * @param canemometer 风速
     */
    public void setCanemometer(String canemometer) {
        this.canemometer = canemometer;
    }

    /**
     * 获取风向
     *
     * @return dogvane - 风向
     */
    public String getDogvane() {
        return dogvane;
    }

    /**
     * 设置风向
     *
     * @param dogvane 风向
     */
    public void setDogvane(String dogvane) {
        this.dogvane = dogvane;
    }

    /**
     * 获取降雨量
     *
     * @return rainfall - 降雨量
     */
    public String getRainfall() {
        return rainfall;
    }

    /**
     * 设置降雨量
     *
     * @param rainfall 降雨量
     */
    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }
}