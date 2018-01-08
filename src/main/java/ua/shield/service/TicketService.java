package ua.shield.service;

import ua.shield.entity.Doctor;
import ua.shield.entity.Ticket;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TicketService {
    List<Ticket> findAllForTwoWeek(Doctor doctor, LocalDate start, LocalDate end);
}
