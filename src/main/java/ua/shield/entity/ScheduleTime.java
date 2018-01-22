package ua.shield.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="schedule_time")
public class ScheduleTime {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer id;

    @Column(name="time")
    private String time;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "schedule_day_id")
    private ScheduleDay scheduleDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ScheduleDay getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(ScheduleDay scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleTime that = (ScheduleTime) o;

        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        return scheduleDay != null ? scheduleDay.equals(that.scheduleDay) : that.scheduleDay == null;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + (scheduleDay != null ? scheduleDay.hashCode() : 0);
        return result;
    }
}
