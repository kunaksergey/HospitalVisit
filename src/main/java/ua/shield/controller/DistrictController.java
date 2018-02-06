package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.entity.District;
import ua.shield.service.DistrictService;

@Controller
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    DistrictService districtService;

    @RequestMapping
    ModelAndView showAll(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("districts",districtService.findAll());
        modelAndView.setViewName("/admin/district/list");
        return modelAndView;
    }

    @RequestMapping(value="add",method = RequestMethod.GET)
    ModelAndView add(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("district",new District());
        modelAndView.setViewName("/admin/district/edit");
        return modelAndView;
    }

    @RequestMapping(value="add",method = RequestMethod.POST)
    String add(District district){
        districtService.save(district);
        return "redirect:/admin/district/list";
    }

    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    ModelAndView edit(@PathVariable Integer id ){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("district",districtService.findOne(id));
        modelAndView.setViewName("/admin/district/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    String save(@ModelAttribute("district")  District district){
        districtService.save(district);
        return "redirect:/district";
    }


}
