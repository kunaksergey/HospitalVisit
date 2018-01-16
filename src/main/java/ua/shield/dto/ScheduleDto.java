package ua.shield.dto;

import ua.shield.entity.Schedule;
import ua.shield.entity.ScheduleDay;
import ua.shield.enum_.EvenOddEnum;
import ua.shield.enum_.WeekDayEnum;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class ScheduleDto {
    private Integer id;
    private String room;
    private String start;
    private String notice;
    private List<ScheduleDay> scheduleDays;

    public ScheduleDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public List<ScheduleDay> getScheduleDays() {
        return scheduleDays;
    }

    public void setScheduleDays(List<ScheduleDay> scheduleDays) {
        this.scheduleDays = scheduleDays;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
