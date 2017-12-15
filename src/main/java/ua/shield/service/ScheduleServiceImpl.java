package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Schedule;
import ua.shield.repository.ScheduleRepository;

/**
 * Created by sa on 14.12.17.
 */
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public Schedule findOne(Integer id){
        return scheduleRepository.findOne(id);
    };
}
