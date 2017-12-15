package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.shield.entity.Role;
import ua.shield.entity.User;
import ua.shield.enum_.RoleEnum;
import ua.shield.service.HospitalService;
import ua.shield.service.SecurityService;
import ua.shield.service.UserService;
import ua.shield.validator.UserFormValidator;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;


/**
 * Created by sa on 09.12.17.
 */
@Controller
public class RegController {
    private static final  String BASE_REG_URI="/registration";
    private static final  String USER_REG_URI=BASE_REG_URI;
    private static final  String DOCTOR_REG_URI=BASE_REG_URI+"/doctor";
    private static final  String ANY_REG_URI=BASE_REG_URI+"/any";
    @Autowired
    UserService userService;
    @Autowired
    HospitalService hospitalService;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserFormValidator userValidator;

    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = USER_REG_URI,method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("regUri", USER_REG_URI);
        return "/user/registration";
    }

    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = USER_REG_URI,method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,Model model) {

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
              return "/user/registration";
        }
        userForm.setRoles(new HashSet<>(Collections.singletonList(new Role(RoleEnum.ROLE_USER))));
        userService.add(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());
        return "redirect:/welcome";
    }

    @PreAuthorize("hasRole(\"ROLE_ADMIN_HOSPITAL\")")
    @RequestMapping(value = DOCTOR_REG_URI, method = RequestMethod.GET)
    public String registrationDoctor(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("regUri", DOCTOR_REG_URI);
        return "/user/registration";
    }

    @PreAuthorize("hasRole(\"ROLE_ADMIN_HOSPITAL\")")
    @RequestMapping(value = DOCTOR_REG_URI, method = RequestMethod.POST)
    public String registrationDoctor(@ModelAttribute("userForm") User userForm, Principal principal,BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/user/registration";
        }
        userForm.setRoles(new HashSet<>(Collections.singletonList(new Role(RoleEnum.ROLE_DOCTOR))));
        userForm.setHospital(userService.findByUsername(principal.getName()).getHospital());
        userService.add(userForm);
        return "redirect:/welcome";
    }

    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @RequestMapping(value = ANY_REG_URI, method = RequestMethod.GET)
    public String registrationAnyUser(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("regUri", ANY_REG_URI);
        model.addAttribute("roles",RoleEnum.values());
        model.addAttribute("hospitals",hospitalService.findAll());
        return "/user/registration";
    }

    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @RequestMapping(value = ANY_REG_URI, method = RequestMethod.POST)
    public String registrationAnyUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
        model.addAttribute("roles",RoleEnum.values());
        return "/user/registration";
        }
        userService.add(userForm);
        return "redirect:/welcome";
    }
}
