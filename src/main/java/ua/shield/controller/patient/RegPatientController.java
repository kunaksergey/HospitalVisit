package ua.shield.controller.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.shield.dto.UserDto;
import ua.shield.entity.Patient;
import ua.shield.entity.User;
import ua.shield.service.PatientService;
import ua.shield.service.SecurityService;
import ua.shield.service.UserService;
import ua.shield.validator.UserFormValidator;

@Controller
public class RegPatientController {
    private static final  String PATIENT_REG_URI ="/registration";

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserFormValidator userValidator;

    @Autowired
    private SecurityService securityService;

    //Реєстрація пацієента
    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = PATIENT_REG_URI,method = RequestMethod.GET)
    public String registrationPatient(Model model) {
        model.addAttribute("userForm", new UserDto());
        model.addAttribute("regUri", PATIENT_REG_URI);
        return "/user/registration";
    }

    //Реєстрація пацієента
    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = PATIENT_REG_URI,method = RequestMethod.POST)
    public String registrationPatient(@ModelAttribute("userForm") UserDto userForm, BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/user/registration";
        }
        User newUser = userService.add(userForm.getUserFromUserDto());
        patientService.add(new Patient(newUser));
        securityService.autoLogin(userForm.getUsername(), userForm.getPassword());
        return "redirect:/welcome";
    }
}
