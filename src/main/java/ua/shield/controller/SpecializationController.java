package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.entity.Specialization;
import ua.shield.service.SpecializationService;

@Controller
@RequestMapping("/specialization")
public class SpecializationController {
    private final SpecializationService specializationService;

    @Autowired
    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @RequestMapping
    ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("specializations", specializationService.findAll());
        modelAndView.setViewName("/admin/specialization/list");
        return modelAndView;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("specialization", new Specialization());
        modelAndView.setViewName("/admin/specialization/edit");
        return modelAndView;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    String add(Specialization specialization) {
        specializationService.save(specialization);
        return "redirect:/admin/specialization/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    ModelAndView edit(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("specialization", specializationService.findOne(id));
        modelAndView.setViewName("/admin/specialization/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    String save(@ModelAttribute("specialization") Specialization specialization) {
        specializationService.save(specialization);
        return "redirect:/specialization";
    }
}
