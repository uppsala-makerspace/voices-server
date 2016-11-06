package se.uppsalamakerspace.iot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.uppsalamakerspace.iot.model.Station;
import se.uppsalamakerspace.iot.model.User;
import se.uppsalamakerspace.iot.repository.StationRepository;
import se.uppsalamakerspace.iot.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by fredl2 on 2016-10-27.
 */
@Controller
public class AdminStationController {

    private final StationRepository stationRepo;
    private final UserRepository userRepo;

    @Autowired
    public AdminStationController(StationRepository stationRepo, UserRepository userRepo) {
        this.stationRepo = stationRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/admin/station")
    public String stationPage(Model model) {
        model.addAttribute("stations", stationRepo.findAll());
        return "admin-station";
    }

    @GetMapping("/admin/station/new")
    public String addStationPage() {
        return "admin-station-new";
    }

    @PostMapping("/admin/station")
    public String addStation(Authentication auth, @ModelAttribute Station station) {
        Optional<User> user = getUserByAuth(auth);
        if(!user.isPresent()) {
            throw new RuntimeException("You are logged in as an non-existent user. You cannot create a station.");
        }
        station.setOwner(user.get());
        station.setUuid(genUuid());
        stationRepo.save(station);
        return "redirect:/admin/station";
    }

    private String genUuid() {
        return UUID.randomUUID().toString();
    }

    private Optional<User> getUserByAuth(Authentication authentication) {
        if(authentication == null || !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return Optional.empty();
        }
        User user = userRepo.findByUserName(authentication.getName());
        if(user == null) {
            return Optional.empty();
        }
        return Optional.of(user);
    }
}
