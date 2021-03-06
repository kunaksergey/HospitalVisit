package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.entity.Doctor;
import ua.shield.entity.Hospital;
import ua.shield.entity.User;
import ua.shield.service.DoctorService;
import ua.shield.service.HospitalService;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private HospitalService hospitalService;

    public List<Doctor> showBySpec(){
    return null;
    }

    public List<Doctor> showByName(){
        return null;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    ModelAndView doctorDetails(@PathVariable Integer id,ModelAndView modelAndView){
        Doctor doctor=doctorService.findOne(id);
        modelAndView.addObject("doctor",doctor);
        modelAndView.setViewName("/doctor/details");
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

}
