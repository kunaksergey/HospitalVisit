package ua.shield.dto;

import ua.shield.entity.ScheduleDay;

import java.util.*;

public class ScheduleDto {
    private Integer id;
    private Integer doctorId;
    private String room;
    private String start;
    private String notice;
    private Set<ScheduleDay> scheduleDaySet;

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

    public Set<ScheduleDay> getScheduleDaySet() {
        return scheduleDaySet;
    }

    public void setScheduleDaySet(Set<ScheduleDay> scheduleDaySet) {
        this.scheduleDaySet = scheduleDaySet;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}
