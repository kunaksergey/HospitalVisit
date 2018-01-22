package ua.shield.service;

import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    Schedule findOne(Integer id);
    List<Schedule> findAllByDoctor(Doctor doctor);
    Schedule save(Schedule schedule);
    void delete(Integer id);
}
