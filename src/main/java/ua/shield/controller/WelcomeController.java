package ua.shield.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sa on 03.12.17.
 */
@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String welcome() {
        return "welcome";
    }

   // @RequestMapping("/error")
    public String error() {
        return "error";
    }
}
