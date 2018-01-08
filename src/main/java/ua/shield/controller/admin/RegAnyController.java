package ua.shield.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.shield.dto.UserDto;
import ua.shield.entity.*;
import ua.shield.enum_.RoleEnum;
import ua.shield.service.*;
import ua.shield.validator.UserFormValidator;

import java.util.Set;

@Controller
public class RegAnyController {
    private static final  String ANY_REG_URI="/registration/any";

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private UserFormValidator userValidator;

    //Реєстрацыя будь якого користувача
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = ANY_REG_URI, method = RequestMethod.GET)
    public String registrationAny(Model model) {
        model.addAttribute("userForm", new UserDto());
        model.addAttribute("regUri", ANY_REG_URI);
        model.addAttribute("roles", RoleEnum.values());
        model.addAttribute("hospitals",hospitalService.findAll());
        return "/user/registration";
    }

    //Реєстрація будь якого користувача
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = ANY_REG_URI, method = RequestMethod.POST)
    public String registrationAny(@ModelAttribute("userForm") UserDto userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles",RoleEnum.values());
            return "/user/registration";
        }

        User newUser = userService.add(userForm.getUserFromUserDto());

        Set<Role> roles = userForm.getRoles();
        if(roles.contains(new Role(RoleEnum.ROLE_USER))){
            patientService.add(new Patient(newUser));
        }
        if(roles.contains(new Role(RoleEnum.ROLE_DOCTOR))){
            Doctor doctor=new Doctor(newUser);
            doctor.setHospital(userForm.getHospital());
            doctorService.add(doctor);
        }
        if(roles.contains(new Role(RoleEnum.ROLE_SUPERVISOR))){
            Supervisor supervisor=new Supervisor(newUser);
            supervisor.setHospital(userForm.getHospital());
            supervisorService.add(supervisor);
        }

        if(roles.contains(new Role(RoleEnum.ROLE_ADMIN))){
            Admin admin=new Admin(newUser);
            adminService.add(admin);
        }
        return "redirect:/welcome";
    }
}
