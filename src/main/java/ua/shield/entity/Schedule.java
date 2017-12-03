package ua.shield.entity;

import ua.shield.enum_.WeekDayEnum;

import java.time.LocalDate;
import java.util.Map;

/**
 * Created by sa on 30.11.17.
 */
public class Schedule {
    private LocalDate start;
    private Map<WeekDayEnum, String> tableSchedule;
    private Doctor doctor;
    private String room;
}
