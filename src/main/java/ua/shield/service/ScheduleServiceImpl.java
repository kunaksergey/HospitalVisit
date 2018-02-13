package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;
import ua.shield.repository.ScheduleRepository;

import java.util.List;

@Service("scheduleService")
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Schedule findOne(Integer id) {
        return scheduleRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Schedule> findAllByDoctor(Doctor doctor) {
        return scheduleRepository.findAllByDoctor(doctor);
    }

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Integer id) {
        scheduleRepository.delete(id);
     }

}
