package com.wheat.cloud.statistic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "weather_yield")

public class WheatYield   implements Serializable {

    @Id
    @Column(name = "city_id")
    private String cityId;

    @Column(name = "yield")
    private String yield;

    @Column(name = "average_product")
    private String averageProduct;

    @Column(name = "area")
    private String area;

    @Column(name = "yield_growth_rate")
    private String yieldGrowthRate;

    @Column(name = "average_product_growth_rate")
    private String averageProductGrowthRate;

    @Column(name = "area_growth_rate")
    private String areaGrowthRate;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public String getAverageProduct() {
        return averageProduct;
    }

    public void setAverageProduct(String averageProduct) {
        this.averageProduct = averageProduct;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getYieldGrowthRate() {
        return yieldGrowthRate;
    }

    public void setYieldGrowthRate(String yieldGrowthRate) {
        this.yieldGrowthRate = yieldGrowthRate;
    }

    public String getAverageProductGrowthRate() {
        return averageProductGrowthRate;
    }

    public void setAverageProductGrowthRate(String averageProductGrowthRate) {
        this.averageProductGrowthRate = averageProductGrowthRate;
    }

    public String getAreaGrowthRate() {
        return areaGrowthRate;
    }

    public void setAreaGrowthRate(String areaGrowthRate) {
        this.areaGrowthRate = areaGrowthRate;
    }
}
