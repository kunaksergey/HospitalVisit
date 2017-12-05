package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.entity.District;
import ua.shield.entity.Hospital;
import ua.shield.service.DistrictService;
import ua.shield.service.HospitalService;

@Controller
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    HospitalService hospitalService;

    @Autowired
    DistrictService districtService;

    @RequestMapping
    ModelAndView showAll(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("hospitals",hospitalService.findAll());
        modelAndView.setViewName("/admin/hospital/list");
        return modelAndView;
    }

    @RequestMapping(value="add",method = RequestMethod.GET)
    ModelAndView add(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("hospital",new Hospital());
        modelAndView.addObject("districts",districtService.findAll());
        modelAndView.setViewName("/admin/hospital/edit");
        return modelAndView;
    }

    @RequestMapping(value="add",method = RequestMethod.POST)
    String add(Hospital hospital){
        hospitalService.save(hospital);
        return "redirect:/admin/hospital/list";
    }

    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    ModelAndView edit(@PathVariable Integer id ){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("hospital",hospitalService.findOne(id));
        modelAndView.addObject("districts",districtService.findAll());
        modelAndView.setViewName("/admin/hospital/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    String save(@ModelAttribute("hospital")  Hospital hospital){
        hospitalService.save(hospital);
        return "redirect:/hospital";
    }

}
