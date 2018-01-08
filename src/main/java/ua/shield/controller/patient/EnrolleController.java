package ua.shield.controller.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.shield.dto.TicketDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Ticket;
import ua.shield.entity.User;
import ua.shield.service.DoctorService;
import ua.shield.service.PatientService;
import ua.shield.service.UserService;

import java.security.Principal;

//@Controller
public class EnrolleController {

    @Autowired
    private UserService userService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    @RequestMapping
    void addTicket(TicketDto ticketDto, Principal principal) {
        User enteredUser = userService.findByUsername(principal.getName());
        Ticket ticket = new Ticket();
        ticket.setTime(ticketDto.getTime());
//        ticket.setDate(ticketDto.getDate().toString());
        ticket.setPatient(patientService.findByUser(enteredUser));
        Doctor doctor = doctorService.findOne(ticketDto.getDoctorId());
        doctor.addTicket(ticket);
        doctorService.update(doctor);
    }
}
