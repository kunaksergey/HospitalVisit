package ua.shield.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sa on 21.12.17.
 */
@Controller
public class WelcomeAdminController {
    @RequestMapping("/admin")
    public String welcomeAdmin() {
        return "/admin/index";
    }
}
