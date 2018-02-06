package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.entity.Doctor;
import ua.shield.service.DoctorService;
import ua.shield.service.HospitalService;

import java.util.List;

@Controller
@RequestMapping("/enroll")
public class EnrollController {
    private final DoctorService doctorService;

    @Autowired
    public EnrollController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping(value="/doctor/{id}",method = RequestMethod.GET)
    ModelAndView doctorDetails(@PathVariable Integer id, ModelAndView modelAndView){
        modelAndView.addObject("doctor",doctorService.findOne(id));
        modelAndView.setViewName("/enroll");
        return modelAndView;
    }
}
