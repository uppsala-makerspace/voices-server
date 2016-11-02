package se.uppsalamakerspace.iot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by fredl2 on 2016-10-27.
 */
@Entity
@Table(name="iot_sensor_metric")
public class SensorMetric {
    @Id
    @Column(name="metric_id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
