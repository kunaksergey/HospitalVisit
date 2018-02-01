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
import ua.shield.validator.UserFormValidator;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserFormValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/user/addRoles", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void test(@RequestBody Set<RoleEnum> roles) {
        Set<Role> collect = roles.stream().map(Role::new).collect(Collectors.toSet());
        System.out.println();
    }

}
