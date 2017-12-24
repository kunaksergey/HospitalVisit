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
import ua.shield.entity.Patient;
import ua.shield.entity.User;
import ua.shield.service.DistrictService;
import ua.shield.service.NewsService;
import ua.shield.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sa on 03.12.17.
 */
@Controller
public class WelcomeController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private DistrictService districtService;

    @RequestMapping("/")
    public ModelAndView welcome(ModelAndView modelAndView) {
        modelAndView.addObject("searchCriteria", new SearchCriteria());
        modelAndView.addObject("news", newsService.findAll());
        modelAndView.addObject("districts", districtService.findAll());
        modelAndView.setViewName("/welcome");
        return modelAndView;
    }

}
