package se.uppsalamakerspace.iot.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se.uppsalamakerspace.iot.model.User;
import se.uppsalamakerspace.iot.repository.UserRepository;

/**
 * Created by fredl2 on 2016-10-27.
 */
@Controller
public class AdminController {

    private final UserRepository userRepo;
    private final Log log = LogFactory.getLog(AdminController.class);

    @Autowired
    public AdminController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @PostMapping("/admin/firstUser")
    public String addFirstUser(@ModelAttribute FirstUserForm userForm) {
        if(!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            return "redirect:/admin";
        }


        User user = new User();
        user.setUserName(userForm.getUsername());
        user.setUserType(User.UserType.ADMIN);
        user.setPasswordHash(BCrypt.hashpw(userForm.getPassword(), BCrypt.gensalt(12)));
        userRepo.save(user);
        log.info("Skapade den första admin-användaren, användarnamn: " + user.getUserName());

        return "redirect:/admin";
    }


    private static class FirstUserForm {
        private String username;
        private String password;
        private String passwordConfirm;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPasswordConfirm() {
            return passwordConfirm;
        }

        public void setPasswordConfirm(String passwordConfirm) {
            this.passwordConfirm = passwordConfirm;
        }
    }
}
