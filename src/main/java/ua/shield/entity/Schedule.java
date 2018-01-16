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
    @JsonProperty("scheduleDetails")
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
}
