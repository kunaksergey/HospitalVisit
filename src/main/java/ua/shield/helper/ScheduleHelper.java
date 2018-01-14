package ua.shield.helper;


import ua.shield.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public class ScheduleHelper {

    public static Schedule defineCurrentSchedule(List<Schedule> scheduleList, LocalDate currentDay) {
        Schedule currentSchedule = null;
        for (Schedule next : scheduleList) {
            if (currentSchedule == null) {
                currentSchedule = next;
            } else {
                if (next.getStart().isAfter(currentSchedule.getStart()) && next.getStart().isBefore(currentDay)) {
                    currentSchedule = next;
                }
            }
        }
        return currentSchedule;
    }
}
