package ua.shield.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ua.shield.enum_.EvenOddEnum;
import ua.shield.enum_.WeekDayEnum;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "schedule_day")
@JsonPropertyOrder(value={ "id","evenOrOdd", "weekDay" })
public class ScheduleDay {
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

    @Column(name="notice")
    private String notice; //заметки

    @Column(name="time")
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = "scheduleDay", orphanRemoval = true)
    @JsonProperty("scheduleTime")
    private Set<ScheduleTime> scheduleTime;//время приема

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

    public Set<ScheduleTime> getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Set<ScheduleTime> scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}
