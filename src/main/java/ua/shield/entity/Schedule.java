package ua.shield.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="schedule")
public class Schedule {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate start;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name="room")
    private String room;

    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = "schedule", orphanRemoval = true)
    @JsonProperty("scheduleDaySet")
    private Set<ScheduleDay> scheduleDaySet;

    @Column(name="notice")
    private String notice;

    public Schedule() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStart(LocalDate start) {
        this.start = start;

    }

    public LocalDate getStart() {
        return start;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (start != null ? !start.equals(schedule.start) : schedule.start != null) return false;
        return doctor != null ? doctor.equals(schedule.doctor) : schedule.doctor == null;
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        return result;
    }
}
