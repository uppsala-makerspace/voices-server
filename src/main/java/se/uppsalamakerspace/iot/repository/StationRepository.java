package se.uppsalamakerspace.iot.repository;

import org.springframework.data.repository.CrudRepository;
import se.uppsalamakerspace.iot.model.Station;

/**
 * Created by fredl2 on 2016-11-01.
 */
public interface StationRepository extends CrudRepository<Station,String> {
}
