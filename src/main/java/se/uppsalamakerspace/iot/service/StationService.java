package se.uppsalamakerspace.iot.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import se.uppsalamakerspace.iot.model.Station;
import se.uppsalamakerspace.iot.repository.StationRepository;

import java.util.Optional;

/**
 * Created by fredl2 on 13/11/16.
 *
 * Service responsible for keeping track of stations
 */
@Service
public class StationService {

    private final StationRepository stationRepo;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class StationNotFoundException extends RuntimeException {
        public StationNotFoundException(String s) {
            super(s);
        }
    }

    public StationService(StationRepository stationRepo) {
        this.stationRepo = stationRepo;
    }

    public Optional<Station> getStationByAuth(Authentication auth) {
        return Optional.ofNullable(stationRepo.findOne(auth.getName()));
    }
}
