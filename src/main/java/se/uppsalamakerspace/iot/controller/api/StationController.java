package se.uppsalamakerspace.iot.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import se.uppsalamakerspace.iot.model.Station;
import se.uppsalamakerspace.iot.repository.StationRepository;

import java.util.Optional;

/**
 * Created by fredl2 on 2016-11-08.
 */

@CrossOrigin
@RestController
public class StationController {

    private final StationRepository stationRepo;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class StationNotFoundException extends RuntimeException {
        public StationNotFoundException(String s) {
            super(s);
        }
    }

    @Autowired
    public StationController(StationRepository stationRepo) {
        this.stationRepo = stationRepo;
    }

    @GetMapping("/api/v1/station/me")
    public Station getMyself(Authentication auth) {
        Optional<Station> station = getStationFromAuth(auth);
        if(!station.isPresent()) {
            throw new StationNotFoundException("Du finns inte");
        }
        return station.get();
    }

    private Optional<Station> getStationFromAuth(Authentication auth) {
        Station station = stationRepo.findOne(auth.getName());
        if(station == null) {
            return Optional.empty();
        }
        return Optional.of(station);
    }
}
