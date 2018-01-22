package ua.shield.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.SortNatural;
import ua.shield.enum_.EvenOddEnum;
import ua.shield.enum_.WeekDayEnum;


import javax.persistence.*;
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
    private EvenOddEnum evenOrOdd;

    @Enumerated(EnumType.STRING)
    @Column(name="weekday")
    private WeekDayEnum weekDay;

    @Column(name="notice")
    private String notice;

    @Column(name="time")
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = "scheduleDay", orphanRemoval = true)
    @JsonProperty("scheduleTimeSet")
    @SortNatural
    @OrderBy("time ASC")
    private Set<ScheduleTime> scheduleTimeSet;

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

    public Set<ScheduleTime> getScheduleTimeSet() {
        return scheduleTimeSet;
    }

    public void setScheduleTimeSet(Set<ScheduleTime> scheduleTimeSet) {
        this.scheduleTimeSet = scheduleTimeSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleDay that = (ScheduleDay) o;

        if (evenOrOdd != that.evenOrOdd) return false;
        if (weekDay != that.weekDay) return false;
        return schedule != null ? schedule.equals(that.schedule) : that.schedule == null;
    }

    @Override
    public int hashCode() {
        int result = evenOrOdd != null ? evenOrOdd.hashCode() : 0;
        result = 31 * result + (weekDay != null ? weekDay.hashCode() : 0);
        result = 31 * result + (schedule != null ? schedule.hashCode() : 0);
        return result;
    }
}
