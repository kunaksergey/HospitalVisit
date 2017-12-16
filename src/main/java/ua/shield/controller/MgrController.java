package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.shield.entity.Hospital;
import ua.shield.entity.Role;
import ua.shield.entity.Specialization;
import ua.shield.entity.User;
import ua.shield.enum_.RoleEnum;
import ua.shield.service.SpecializationService;
import ua.shield.service.UserService;

import java.security.Principal;
import java.util.List;

/**
 * Created by sa on 11.12.17.
 */
@Controller
@RequestMapping("/mgr")
public class MgrController {
    @Autowired
    UserService userService;

    @Autowired
    SpecializationService specializationService;

    @RequestMapping("doctor")
    String showDoctorList(Model model, Principal principal){
        Hospital hospital=userService.findByUsername(principal.getName()).getHospital();
        model.addAttribute("doctors",userService.findByHospitalAndRoles(hospital, new Role(RoleEnum.ROLE_DOCTOR)));
        return "/mgr/doctor/list";
    }

    @RequestMapping(value = "doctor/edit/{id}",method = RequestMethod.GET)
    String edit(@PathVariable Integer id, Model model,Principal principal){
        User doctor=userService.findOne(id);
//        User adminHospital=userService.findByUsername(principal.getName());
        User adminHospital=userService.findByUsername("hospital");//delete
        doctor=doctor.getHospital().equals(adminHospital.getHospital())?doctor:null;
        List<Specialization> allSpecialization = specializationService.findAll();
        allSpecialization.removeAll(doctor.getSpecialization());
        model.addAttribute("doctor",doctor);
        model.addAttribute("specialization",allSpecialization);
        model.addAttribute("roles",new Role[]{new Role(RoleEnum.ROLE_DOCTOR)});
        return "/mgr/doctor/edit";
    }

    @RequestMapping(value = "doctor/save",method = RequestMethod.POST)
    String save(@ModelAttribute("user") User user, Model model){
        System.out.println();
        System.out.println(user);
        //userService.update(user);
        return "redirect:/admin/users";
    }


}
