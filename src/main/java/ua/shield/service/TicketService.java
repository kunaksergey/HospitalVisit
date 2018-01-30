package ua.shield.service;

import ua.shield.domen.DateRange;
import ua.shield.entity.Doctor;
import ua.shield.entity.Patient;
import ua.shield.entity.Schedule;
import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;

import java.time.LocalDate;
import java.util.List;

public interface TicketService {
    List<Ticket> findAllForTwoWeek(Doctor doctor, LocalDate start, LocalDate end);

    List<Ticket> assambledInRangeByDoctor(DateRange range, Doctor doctor);

    Ticket save(Ticket ticket);

    List<Ticket> findAllByPatient(Patient patient);
    List<Ticket> findAllByDoctor(Doctor doctor);

    List<Ticket> findAllByDoctorAndStatus(Doctor doctor, StatusTicket statusTicket);
    List<Ticket> findAllByPatientAndStatus(Patient patient, StatusTicket statusTicket);

    List<Ticket> findAllByDoctorInRange(Doctor doctor,DateRange range);
    List<Ticket> findAllByPatientInRange( Patient patient,DateRange range);

    List<Ticket> findAllByPatientAndDateStartWith(Patient patient, DateRange range);
    List<Ticket> findAllByDoctorAndDateStartWith(Doctor doctor, DateRange range);
    Ticket findOne(Integer id);
    Ticket add(Ticket ticket);
    Ticket update(Ticket ticket);
}
