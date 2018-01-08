package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.shield.entity.Doctor;
import ua.shield.entity.Ticket;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
   List<Ticket> findAllByDoctorAndDateGreaterThanEqualAndDateLessThanEqual(Doctor doctor, LocalDate after, LocalDate before);
}
