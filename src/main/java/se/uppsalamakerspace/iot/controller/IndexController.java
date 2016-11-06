package se.uppsalamakerspace.iot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by fredl2 on 06/11/16.
 */

@Controller
public class IndexController {
    @GetMapping("/")
    public String redirect() {
        return "redirect:/admin";
    }
}
