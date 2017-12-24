package ua.shield.controller.supervisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.shield.dto.DoctorDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Role;
import ua.shield.entity.Supervisor;
import ua.shield.entity.User;
import ua.shield.enum_.RoleEnum;
import ua.shield.service.*;

import java.security.Principal;
import java.util.Objects;

/**
 * Created by sa on 11.12.17.
 */
@Controller
@RequestMapping("/supervisor")
public class SupervisorController {


}
