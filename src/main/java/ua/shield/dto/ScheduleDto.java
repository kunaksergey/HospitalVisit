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
    private Map<EvenOddEnum, Map<WeekDayEnum, Set<String>>> detailsMap;


    public ScheduleDto(Schedule schedule) {
        this.id = schedule.getId();
        this.room = schedule.getRoom();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.start = schedule.getStart().format(formatter);
        this.notice=schedule.getNotice();
        this.detailsMap = detailsSetToMap(schedule.getScheduleDaySet());
    }

    //превращаем ScheduleDay в map
    private Map<EvenOddEnum, Map<WeekDayEnum, Set<String>>> detailsSetToMap(Set<ScheduleDay> detailsSet) {
        Map<EvenOddEnum, Map<WeekDayEnum, Set<String>>> detailsMap = new HashMap<>();
//        for (ScheduleDay detail : detailsSet) {
//            Map<WeekDayEnum, Set<String>> orDefaultEven = detailsMap.getOrDefault(detail.getEvenOrOdd(), new HashMap<>());
//            detailsMap.put(detail.getEvenOrOdd(), orDefaultEven);
//            Set<String> orDefaultWeekDay = orDefaultEven.getOrDefault(detail.getWeekDay(), new TreeSet<>());
//            orDefaultEven.put(detail.getWeekDay(), orDefaultWeekDay);
//            orDefaultWeekDay.add(detail.);
//        }
        return detailsMap;
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

    public Map<EvenOddEnum, Map<WeekDayEnum, Set<String>>> getDetailsMap() {
        return detailsMap;
    }

    public void setDetailsMap(Map<EvenOddEnum, Map<WeekDayEnum, Set<String>>> detailsMap) {
        this.detailsMap = detailsMap;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
