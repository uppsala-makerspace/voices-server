package se.uppsalamakerspace.iot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.uppsalamakerspace.iot.model.Station;
import se.uppsalamakerspace.iot.repository.StationRepository;

import java.util.UUID;

/**
 * Created by fredl2 on 2016-10-27.
 */
@Controller
public class AdminStationController {

    private final StationRepository stationRepo;

    @Autowired
    public AdminStationController(StationRepository stationRepo) {
        this.stationRepo = stationRepo;
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
    public String addStation(@ModelAttribute Station station) {
        station.setUuid(genUuid());
        stationRepo.save(station);
        return "redirect:/admin/station";
    }

    private String genUuid() {
        return UUID.randomUUID().toString();
    }
}
