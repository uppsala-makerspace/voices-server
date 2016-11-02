package se.uppsalamakerspace.iot.repository;

import org.springframework.data.repository.CrudRepository;
import se.uppsalamakerspace.iot.model.SensorMetric;

/**
 * Created by fredl2 on 2016-10-27.
 */
public interface SensorMetricRepository extends CrudRepository<SensorMetric,Long> {
}
