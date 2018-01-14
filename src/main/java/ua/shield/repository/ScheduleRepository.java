package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    List<Schedule> findAllByStartLessThanEqualAndDoctorOrderByStart(LocalDate start, Doctor doctor);
    List<Schedule> findAllByDoctor(Doctor doctor);
}
