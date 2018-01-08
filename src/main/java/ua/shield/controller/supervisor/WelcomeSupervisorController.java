package ua.shield.controller.supervisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.shield.entity.User;
import ua.shield.service.PatientService;
import ua.shield.service.SupervisorService;
import ua.shield.service.UserService;

import java.security.Principal;

/**
 * Created by sa on 21.12.17.
 */
@Controller
public class WelcomeSupervisorController {
    @Autowired
    private UserService userService;

    @Autowired
    private SupervisorService supervisorService;

    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @RequestMapping("/supervisor")
    public String welcomePatient(Model model, Principal principal) {
        User enteredUser = userService.findByUsername(principal.getName());
        model.addAttribute("supervisor",supervisorService.findByUser(enteredUser));
        return "/supervisor/index";
    }
}
