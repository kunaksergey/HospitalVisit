package ua.shield.controller.supervisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.shield.dto.DoctorDto;
import ua.shield.dto.UserDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Supervisor;
import ua.shield.entity.User;
import ua.shield.service.DoctorService;
import ua.shield.service.SpecializationService;
import ua.shield.service.SupervisorService;
import ua.shield.service.UserService;
import ua.shield.validator.UserFormValidator;

import java.security.Principal;
import java.util.Objects;

import static ua.shield.controller.supervisor.DoctorManageController.BASE_URI;

/**
 * Created by sa on 22.12.17.
 */
@Controller
@RequestMapping(BASE_URI)
public class DoctorManageController {
    public static final String BASE_URI = "/supervisor";
    private static final String DOCTOR_REG_URI = "/doctor/registration";

    @Autowired
    private UserFormValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private SpecializationService specializationService;

    //Реєстрація лікаря
    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @RequestMapping(value = DOCTOR_REG_URI, method = RequestMethod.GET)
    public String registrationDoctor(Model model) {
        model.addAttribute("userForm", new UserDto());
        model.addAttribute("regUri", BASE_URI + DOCTOR_REG_URI);
        return "/user/registration";
    }

    //Реєстрація лікаря
    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @RequestMapping(value = DOCTOR_REG_URI, method = RequestMethod.POST)
    public String registrationDoctor(@ModelAttribute("userForm") UserDto userForm, BindingResult bindingResult, Principal principal) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/user/registration";
        }
        User newUser = userService.add(userForm.getUserFromUserDto());
        Doctor doctor = new Doctor(newUser);
        User enteredUser = userService.findByUsername(principal.getName());
        doctor.setHospital(supervisorService.findByUser(enteredUser).getHospital());
        doctorService.add(doctor);
        return "redirect:/supervisor";
    }


    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @RequestMapping("doctors")
    String showList(Model model, Principal principal) {
        User enteredUser = userService.findByUsername(principal.getName());
        Supervisor supervisor = supervisorService.findByUser(enteredUser);
        model.addAttribute("doctors", supervisor.getHospital().getDoctors());
        return "/supervisor/doctor/list";
    }

//    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @RequestMapping(value = "doctor/edit/{id}", method = RequestMethod.GET)
    String edit(@PathVariable Integer id, Model model, Principal principal) {
//        User enteredUser = userService.findByUsername(principal.getName());
//        Supervisor supervisor = supervisorService.findByUser(enteredUser);
        Supervisor supervisor =supervisorService.findOne(2);
        Doctor doctor = supervisor.getHospital().getDoctors().stream()
                .filter(d -> Objects.equals(d.getId(), id))
                .findFirst()
                .orElse(null);
        if (doctor == null) {
            return "redirect:/error";
        }
        model.addAttribute("doctorForm", new DoctorDto(doctor));
        model.addAttribute("specializations", specializationService.findAll());
        return "/supervisor/doctor/edit";
    }

    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @RequestMapping(value = "doctor/save", method = RequestMethod.POST)
    String save(@ModelAttribute("doctorForm") DoctorDto doctorDto,BindingResult bindingResult, Principal principal) {
        User enteredUser = userService.findByUsername(principal.getName());
        Supervisor supervisor = supervisorService.findByUser(enteredUser);
        Doctor doctor = supervisor.getHospital().getDoctors().stream()
                .filter(d -> Objects.equals(d.getId(), doctorDto.getId()))
                .findFirst()
                .orElse(null);
        if (doctor == null) {
            return "redirect:/error";
        }
        User doctorUser = doctor.getUser();
        doctorUser.setFullName(doctorDto.getFullName());
        doctorUser.setBirthday(doctorDto.getBirthDay());
        doctorUser.setPhone(doctorDto.getPhone());
        doctorUser.setEmail(doctorDto.getEmail());
        doctorUser.setEnabled(doctorDto.isEnable());
        doctor.setSpecializations(doctorDto.getSpecializations());
        doctorService.update(doctor);
        return "redirect:/supervisor/doctors";
    }



}

