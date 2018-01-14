package ua.shield.service;

import ua.shield.domen.DateRange;
import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;
import ua.shield.entity.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketService {
    List<Ticket> findAllForTwoWeek(Doctor doctor, LocalDate start, LocalDate end);
    List<Ticket> findAllInRangeByDoctor(DateRange range, Doctor doctor);
    List<Ticket> assambledInRangeByDoctor(DateRange range, Doctor doctor);
    Ticket save(Ticket ticket);
}
