package statistic.wheat.entity;


import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "city_mapping")
public class CityMapping {


    @Id
    @Column(name = "city_id")   //城市id
    private Integer cityId;

    @Column(name = "city_name")   //城市名
    private String cityName;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
