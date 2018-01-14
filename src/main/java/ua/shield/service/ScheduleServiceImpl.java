package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;
import ua.shield.repository.ScheduleRepository;

import java.time.LocalDate;
import java.util.List;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule findOne(Integer id) {
        return scheduleRepository.findOne(id);
    }

    @Override
    public List<Schedule> findAllByDoctor(Doctor doctor) {
        return scheduleRepository.findAllByDoctor(doctor);
    }

}
