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
    private PatientService patientService;

}
