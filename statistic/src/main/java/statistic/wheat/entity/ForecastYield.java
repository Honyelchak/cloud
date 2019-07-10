package statistic.wheat.entity;


import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "forecast_yield")
public class ForecastYield {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "update_time")
    private String updateTime;
    @Column(name = "city_id")
    private String cityid;
    @Column(name = "city_name")
    private String cityname;
    private Double yield;
    @Column(name = "yield_per_mu")
    private double yieldPerMu;
    private Double area;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Double getYield() {
        return yield;
    }

    public void setYield(Double yield) {
        this.yield = yield;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getYieldPerMu() {
        return yieldPerMu;
    }

    public void setYieldPerMu(double yieldPerMu) {
        this.yieldPerMu = yieldPerMu;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
