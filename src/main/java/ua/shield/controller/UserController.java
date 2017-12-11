package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.shield.entity.Chield;
import ua.shield.entity.Role;
import ua.shield.entity.User;
import ua.shield.enum_.RoleEnum;
import ua.shield.service.SecurityService;
import ua.shield.service.UserService;
import ua.shield.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sa on 06.12.17.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @RequestMapping(value = "/user/addChield", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Set<Chield> addChield(@ModelAttribute Chield chield, BindingResult bindingResult, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        user.addChield(chield);
        return userService.update(user).getChields();
    }

    @RequestMapping(value = "/user/getChield", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Set<Chield> getChield(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return user.getChields();
    }

    @RequestMapping(value = "/user/addRoles", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void test(@RequestBody Set<RoleEnum> roles) {
        Set<Role> collect = roles.stream().map(Role::new).collect(Collectors.toSet());
        System.out.println();
    }

}
