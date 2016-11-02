package se.uppsalamakerspace.iot.controller.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fredl2 on 2016-11-01.
 */

@RestController
public class Ping {
    @GetMapping("/api/v1/ping")
    public String sayPing(Authentication auth) {
        return "Ping back atcha, " + auth.getName();
    }
}
