package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.service.DistrictService;
import ua.shield.service.NewsService;

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
        modelAndView.addObject("news", newsService.findAll());
        modelAndView.addObject("districts", districtService.findAll());
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    // @RequestMapping("/error")
    public String error() {
        return "error";
    }
}
