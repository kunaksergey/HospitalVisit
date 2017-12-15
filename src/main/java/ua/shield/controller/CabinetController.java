package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.entity.User;
import ua.shield.service.UserService;

import java.security.Principal;

/**
 * Created by sa on 09.12.17.
 */
@Controller
public class CabinetController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/cabinet",method= RequestMethod.GET)
    ModelAndView cabinet(Principal principal, ModelAndView modelAndView){
        User user = userService.findByUsername(principal.getName());
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/user/cabinet");
        return modelAndView;
    }
}
