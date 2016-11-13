package se.uppsalamakerspace.iot.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import se.uppsalamakerspace.iot.model.VoiceMessage;
import se.uppsalamakerspace.iot.repository.StationRepository;
import se.uppsalamakerspace.iot.repository.VoiceMessageRepository;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by fredl2 on 2016-11-08.
 */

@RestController
public class VoiceMessageController {

    private final VoiceMessageRepository messageRepo;
    private final StationRepository stationRepo;

    @Autowired
    public VoiceMessageController(VoiceMessageRepository messageRepo, StationRepository stationRepo) {
        this.messageRepo = messageRepo;
        this.stationRepo = stationRepo;
    }

    @CrossOrigin
    @PostMapping("/api/v1/message")
    public void postMessage(Authentication auth, @RequestBody VoiceMessage message) {
        if(message.getUuid() == null) {
            message.setUuid(UUID.randomUUID().toString());
        }
        if(message.getTimestamp() == null) {
            message.setTimestamp(Calendar.getInstance().getTime());
        }
        if(message.getStation() == null && authIsStation(auth)) {
            message.setStation(stationRepo.findOne(auth.getName()));
        }
        message.setNumberPlaybacks(0L);
        messageRepo.save(message);
    }

    private boolean authIsStation(Authentication auth) {
        return auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STATION"));
    }
}
