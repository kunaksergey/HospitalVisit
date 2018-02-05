package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.shield.entity.Hospital;
import ua.shield.entity.Role;
import ua.shield.entity.User;
import ua.shield.enum_.RoleEnum;
import ua.shield.service.HospitalService;
import ua.shield.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
   private UserService userService;

    @Autowired
    private HospitalService hospitalService;

    @RequestMapping(value = "/users")
    String showAll(Model model){
        model.addAttribute("users",userService.findAll());
        return "/admin/user/list";
    }

    @RequestMapping(value = "/users/{role}")
    String showAll(@PathVariable RoleEnum role, Model model){
        model.addAttribute("users",userService.findByRoles(new Role(role)));
        return "/admin/user/list";
    }

    @RequestMapping(value = "/user/edit/{id}",method = RequestMethod.GET)
    String edit(@PathVariable Integer id, Model model){
        model.addAttribute("user",userService.findOne(id));
        model.addAttribute("hospitals",hospitalService.findAll());
        model.addAttribute("roles",RoleEnum.values());
        return "/admin/user/edit";
    }

    @RequestMapping(value = "user/save",method = RequestMethod.POST)
    String save(@ModelAttribute("user") User user, Model model){
        System.out.println();
        System.out.println(user);
        //userService.update(user);
        return "redirect:/admin/users";
    }



}
