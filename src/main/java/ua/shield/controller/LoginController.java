package ua.shield.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sa on 09.12.17.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login",method= RequestMethod.GET)
    public String login(Model model, String error, String logout){
        if(error!=null){
            model.addAttribute("error","Користувач або пароль не вірний");
        }
        return "/user/login";
    }
}
