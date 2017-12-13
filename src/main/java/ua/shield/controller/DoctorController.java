package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.entity.Doctor;
import ua.shield.entity.Hospital;
import ua.shield.service.DoctorService;
import ua.shield.service.HospitalService;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    HospitalService hospitalService;

    public List<Doctor> showBySpec(){
    return null;
    }

    public List<Doctor> showByName(){
        return null;
    }

    @RequestMapping(value="bred",method = RequestMethod.GET)
    ModelAndView showAll(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("doctors",doctorService.findAll());
        modelAndView.setViewName("/admin/doctor/list");
        return modelAndView;
    }

    @RequestMapping(value="add",method = RequestMethod.GET)
    ModelAndView add(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("doctor",new Doctor());
        modelAndView.addObject("hospitals",hospitalService.findAll());
        modelAndView.setViewName("/admin/doctor/edit");
        return modelAndView;
    }

    @RequestMapping(value="add",method = RequestMethod.POST)
    String add(Doctor doctor){
//        doctorService.save(doctor);
        return "redirect:/admin/doctor/list";
    }

//    @RequestMapping(value="schedule",method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
//    @ResponseBody
//    ShedDel shedule(){
//        ShedDel shedDel = new ShedDel();
//        return shedDel;
//    }

}
