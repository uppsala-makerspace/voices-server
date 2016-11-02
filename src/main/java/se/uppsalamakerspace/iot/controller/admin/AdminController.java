package se.uppsalamakerspace.iot.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by fredl2 on 2016-10-27.
 */
@Controller
public class AdminController {
    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
