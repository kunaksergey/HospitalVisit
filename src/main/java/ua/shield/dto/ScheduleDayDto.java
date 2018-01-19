package ua.shield.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import ua.shield.entity.ScheduleTime;
import ua.shield.enum_.EvenOddEnum;
import ua.shield.enum_.WeekDayEnum;

import javax.persistence.*;
import java.util.Set;

public class ScheduleDayDto {
    private EvenOddEnum evenOrOdd;

    @Enumerated(EnumType.STRING)
    @Column(name="weekday")
    private WeekDayEnum weekDay;

    @Column(name="notice")
    private String notice;

    @Column(name="time")
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = "scheduleDay", orphanRemoval = true)
    @JsonProperty("scheduleTime")
    private Set<ScheduleTime> scheduleTime;

}
