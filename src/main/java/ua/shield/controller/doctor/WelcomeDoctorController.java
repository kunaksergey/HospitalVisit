package ua.shield.controller.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.shield.converter.DoctorEntityDtoConverter;
import ua.shield.entity.User;
import ua.shield.service.DoctorService;
import ua.shield.service.PatientService;
import ua.shield.service.UserService;

import java.security.Principal;

@Controller
public class WelcomeDoctorController {
    private final UserService userService;
    private final DoctorEntityDtoConverter doctorConverter;
    private final DoctorService doctorService;

    @Autowired
    public WelcomeDoctorController(UserService userService, DoctorEntityDtoConverter doctorConverter, DoctorService doctorService) {
        this.userService = userService;
        this.doctorConverter = doctorConverter;
        this.doctorService = doctorService;
    }


    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @RequestMapping("/doctor")
    public String welcomeDoctor(Model model, Principal principal) {
        model.addAttribute("doctor",doctorConverter.createFromEntity(doctorService.findByName(principal.getName())));
        return "/doctor/cabinet";
    }
}
