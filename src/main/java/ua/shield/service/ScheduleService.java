package ua.shield.service;

import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;

/**
 * Created by sa on 14.12.17.
 */
public interface ScheduleService {
    Schedule findOne(Integer id);
    Schedule getCurrentSchedule(Doctor doctor);
}
