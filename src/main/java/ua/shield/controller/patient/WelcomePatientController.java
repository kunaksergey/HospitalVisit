package ua.shield.controller.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.shield.dto.UserShortDto;
import ua.shield.entity.User;
import ua.shield.service.PatientService;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/patient/cabinet")
public class WelcomePatientController {
    private final PatientService patientService;

    @Autowired
    public WelcomePatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping()
    public String welcomePatient(Model model, Principal principal) {
        User enteredUser = patientService.findByName(principal.getName()).getUser();
        model.addAttribute("user", new UserShortDto(enteredUser));
        return "/patient/cabinet";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method= RequestMethod.POST)
    public String update(@ModelAttribute("user") UserShortDto userDto, Principal principal) {
        User user = patientService.findByName(principal.getName()).getUser();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        user.setBirthday(LocalDate.parse(userDto.getBirthday(),formatter));
        return "/patient/cabinet";
    }

}
