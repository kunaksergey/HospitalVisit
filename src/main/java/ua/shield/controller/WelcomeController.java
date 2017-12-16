package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.domen.AjaxResponseBody;
import ua.shield.domen.SearchCriteria;
import ua.shield.dto.DoctorDto;
import ua.shield.service.DistrictService;
import ua.shield.service.NewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sa on 03.12.17.
 */
@Controller
public class WelcomeController {
    @Autowired
    NewsService newsService;

    @Autowired
    DistrictService districtService;

    @RequestMapping("/")
    public ModelAndView welcome(ModelAndView modelAndView) {
        modelAndView.addObject("searchCriteria", new SearchCriteria());
        modelAndView.addObject("news", newsService.findAll());
        modelAndView.addObject("districts", districtService.findAll());
        modelAndView.setViewName("/welcome");
        return modelAndView;
    }

    @RequestMapping("/admin")
    public String welcomeAdmin() {
        return "/admin/index";
    }

    @RequestMapping("/doctor")
    public String welcomeDoctor() {
        return "/doctor/index";
    }

    @RequestMapping("/mgr")
    public String welcomeMgr() {
        return "/mgr/index";
    }

    @RequestMapping("/patient")
    public String welcomePatient() {
        return "/patient/index";
    }

    // @RequestMapping("/error")
    public String error() {
        return "error";
    }
}
