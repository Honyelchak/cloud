package statistic.wheat.entity;


import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "data_sum")
public class IndexData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "update_time")
    private String updateTime;

    @Column(name = "production_prediction")
    private double prediction;

    @Column(name = "area")
    private double area;

    @Column(name = "data_total")
    private double dataTotal;

    @Column(name = "solution_total")
    private double solutionTotal;

    @Column(name = "warn_frequent")
    private double warnFrequent;

    @Column(name = "repository")
    private double repository;

    @Column(name = "thing_data")
    private double thingData;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public double getPrediction() {
        return prediction;
    }

    public void setPrediction(double prediction) {
        this.prediction = prediction;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getDataTotal() {
        return dataTotal;
    }

    public void setDataTotal(double dataTotal) {
        this.dataTotal = dataTotal;
    }

    public double getSolutionTotal() {
        return solutionTotal;
    }

    public void setSolutionTotal(double solutionTotal) {
        this.solutionTotal = solutionTotal;
    }

    public double getWarnFrequent() {
        return warnFrequent;
    }

    public void setWarnFrequent(double warnFrequent) {
        this.warnFrequent = warnFrequent;
    }

    public double getRepository() {
        return repository;
    }

    public void setRepository(double repository) {
        this.repository = repository;
    }

    public double getThingData() {
        return thingData;
    }

    public void setThingData(double thingData) {
        this.thingData = thingData;
    }
}
