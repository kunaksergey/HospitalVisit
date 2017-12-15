package ua.shield.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.shield.enum_.WeekDayEnum_old;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sa on 08.12.17.
 * Прием врача на день
 */
@Entity
@Table(name="day")
public class Day {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="notice")
    private String notice;

    @Column(name="is_even")
    private Boolean isEven;//четная/нечетная неделя

    @Column(name="weekday")
    private WeekDayEnum_old weekDay;//день недели

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "day", orphanRemoval = true)
    @OrderBy(value = "time")
    private Set<TimeTicket> timeTickets;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public WeekDayEnum_old getWeekDay() {
        return weekDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isEven() {
        return isEven;
    }

    public void setEven(Boolean even) {
        isEven = even;
    }

    public Set<TimeTicket> getTimeTickets() {
        return timeTickets;
    }

    public void setTimeTickets(Set<TimeTicket> timeTickets) {
        this.timeTickets = timeTickets;
    }

    public void setWeekDay(WeekDayEnum_old weekDay) {
        this.weekDay = weekDay;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day = (Day) o;

        if (isEven != day.isEven) return false;
        return weekDay == day.weekDay;
    }

    @Override
    public int hashCode() {
        int result = isEven != null ? isEven.hashCode() : 0;
        result = 31 * result + (weekDay != null ? weekDay.hashCode() : 0);
        return result;
    }
}
