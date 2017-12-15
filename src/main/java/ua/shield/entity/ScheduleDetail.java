package ua.shield.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ua.shield.enum_.EvenOddEnum;
import ua.shield.enum_.WeekDayEnum;
import ua.shield.enum_.WeekDayEnum_old;

import javax.persistence.*;

/**
 * Created by sa on 14.12.17.
 */
@Entity
@Table(name = "schedule_details")
@JsonPropertyOrder(value={ "id","evenOrOdd", "weekDay", "time"})
public class ScheduleDetail {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="even_or_odd")
    @Enumerated(EnumType.STRING)
    private EvenOddEnum evenOrOdd;//четная/нечетная неделя

    @Enumerated(EnumType.STRING)
    @Column(name="weekday")
    private WeekDayEnum weekDay;//день недели

    @Column(name="time")
    private String time;//время приема

    @Column(name="notice")
    private String notice; //заметки

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @OrderBy(value = "time")
//    private Set<TimeTicket> timeTickets;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EvenOddEnum getEvenOrOdd() {
        return evenOrOdd;
    }

    public void setEvenOrOdd(EvenOddEnum evenOrOdd) {
        this.evenOrOdd = evenOrOdd;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public WeekDayEnum getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDayEnum weekDay) {
        this.weekDay = weekDay;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
