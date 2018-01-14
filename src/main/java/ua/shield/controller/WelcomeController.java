package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.domen.SearchCriteria;
import ua.shield.service.DistrictService;
import ua.shield.service.NewsService;

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

    @RequestMapping("/error-page")
    public String error() {
        return "error";
    }

}
