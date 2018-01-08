package ua.shield.controller.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.shield.entity.User;
import ua.shield.service.PatientService;
import ua.shield.service.UserService;

import java.security.Principal;

/**
 * Created by sa on 21.12.17.
 */
@Controller
public class WelcomePatientController {
    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/patient")
    public String welcomePatient(Model model, Principal principal) {
        User enteredUser = userService.findByUsername(principal.getName());
        model.addAttribute("patient",patientService.findByUser(enteredUser));
        return "/patient/index";
    }

}
