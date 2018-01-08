package ua.shield.controller.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.shield.entity.User;
import ua.shield.service.DoctorService;
import ua.shield.service.PatientService;
import ua.shield.service.UserService;

import java.security.Principal;

/**
 * Created by sa on 21.12.17.
 */
@Controller
public class WelcomeDoctorController {
    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @RequestMapping("/doctor")
    public String welcomeDoctor(Model model, Principal principal) {
        User enteredUser = userService.findByUsername(principal.getName());
        model.addAttribute("patient",doctorService.findByUser(enteredUser));
        return "/doctor/index";
    }
}
