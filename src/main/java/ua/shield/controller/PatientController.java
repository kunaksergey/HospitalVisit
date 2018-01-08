package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.entity.Chield;
import ua.shield.entity.Patient;
import ua.shield.entity.User;
import ua.shield.service.PatientService;
import ua.shield.service.UserService;

import java.security.Principal;
import java.util.Set;

/**
 * Created by sa on 21.12.17.
 */
@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/addChield", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Set<Chield> addChield(@ModelAttribute Chield chield, BindingResult bindingResult, Principal principal) {
        User enteredUser = userService.findByUsername(principal.getName());
        Patient patient = patientService.findByUser(enteredUser);
        patient.addChield(chield);
        return patientService.update(patient).getChields();
    }

    @RequestMapping(value = "getChield", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Set<Chield> getChield(Principal principal) {
        User enteredUser = userService.findByUsername(principal.getName());
        Patient patient = patientService.findByUser(enteredUser);
        return patient.getChields();
    }
}
