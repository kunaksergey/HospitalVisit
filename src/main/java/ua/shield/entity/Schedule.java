package ua.shield.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by sa on 30.11.17.
 * Расписание врача
 */
@Entity
@Table(name="schedule")
public class Schedule {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date start;//начало работы расписания

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;//врач

    @Column(name="room")
    private String room;//кабинет приема

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "schedule", orphanRemoval = true)
    @JsonProperty("scheduleDetails")
    private Set<ScheduleDetail> detailsSet;

    public Schedule() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Set<ScheduleDetail> getDetailsSet() {
        return detailsSet;
    }

    public void setDetailsSet(Set<ScheduleDetail> detailsSet) {
        this.detailsSet = detailsSet;
    }
}
