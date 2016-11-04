package se.uppsalamakerspace.iot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import se.uppsalamakerspace.iot.model.User;
import se.uppsalamakerspace.iot.repository.UserRepository;

/**
 * Created by fredl2 on 03/11/16.
 */

@Controller
public class AdminLoginController {

    private final UserRepository userRepo;

    @Autowired
    public AdminLoginController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/admin/login")
    public String loginPage() {
        if(userRepo.findAllByUserType(User.UserType.ADMIN).isEmpty()) {
            return "first-start";
        }
        return "admin-login";
    }
}
