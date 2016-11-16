package se.uppsalamakerspace.iot.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import se.uppsalamakerspace.iot.model.Station;
import se.uppsalamakerspace.iot.model.Vote;
import se.uppsalamakerspace.iot.model.VoteMessage;
import se.uppsalamakerspace.iot.repository.VoiceMessageRepository;
import se.uppsalamakerspace.iot.repository.VoteRepository;
import se.uppsalamakerspace.iot.service.StationService;
import se.uppsalamakerspace.iot.service.VoteService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by fredl2 on 2016-11-15.
 *
 * Handles incoming votes on voice messages.
 */
@RestController
public class VoteController {

    private final VoteService voteService;
    private final StationService stationService;

    @Autowired
    public VoteController(VoteService voteService, StationService stationService) {
        this.voteService = voteService;
        this.stationService = stationService;
    }

    @PostMapping("/api/v1/vote")
    public void receiveVotes(Authentication auth, @RequestBody List<VoteMessage> messages) {
        Station station = stationService
                .getStationByAuth(auth)
                .orElseThrow(() -> stationNotFound(auth));

        voteService.handleVoteMessages(station, messages);
    }

    private RuntimeException stationNotFound(Authentication auth) {
        return new StationService.StationNotFoundException("Station med ID " + auth.getName() + " finns inte.");
    }
}
