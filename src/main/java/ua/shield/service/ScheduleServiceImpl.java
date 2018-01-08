package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;
import ua.shield.repository.ScheduleRepository;

import java.util.Date;
import java.util.Iterator;

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
    }

    @Override
    public Schedule getCurrentSchedule(Doctor doctor) {
        Schedule currentSchedule=null;
        Iterator<Schedule> iterator = doctor.getSchedules().iterator();
        while (iterator.hasNext()){
            Schedule next = iterator.next();
            if(currentSchedule==null){
                currentSchedule=next;
            }else {
                if(next.getStart().after(currentSchedule.getStart())&&next.getStart().before(new Date())){
                    currentSchedule=next;
                }
            }

        }
        return currentSchedule;
    }

    ;
}
