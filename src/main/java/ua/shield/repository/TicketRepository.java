package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.shield.entity.Doctor;
import ua.shield.entity.Patient;
import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
   List<Ticket> findAllByDoctorAndDateGreaterThanEqualAndDateLessThanEqual(Doctor doctor, LocalDate after, LocalDate before);

   List<Ticket> findAllByPatientAndDateBetween(Patient patient,LocalDate start,LocalDate end);
   List<Ticket> findAllByDoctorAndDateBetween(Doctor doctor,LocalDate start,LocalDate end);

   List<Ticket> findAllByPatientAndDateGreaterThanEqual(Patient patient,LocalDate start);
   List<Ticket> findAllByPatientAndDateGreaterThanEqual(Doctor doctor,LocalDate start);

   List<Ticket> findAllByPatientAndStatus(Patient patient,StatusTicket statusTicket);
   List<Ticket> findAllByDoctorAndStatus(Doctor doctor,StatusTicket statusTicket);

   List<Ticket> findAllByPatient(Patient patient);
   List<Ticket> findAllByDoctor(Doctor doctor);
}
