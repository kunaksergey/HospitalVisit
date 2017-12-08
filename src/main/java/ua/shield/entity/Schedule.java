package ua.shield.entity;

import org.hibernate.annotations.GeneratorType;
import org.springframework.format.annotation.DateTimeFormat;
import ua.shield.enum_.WeekDayEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;//врач

    @Column(name="room")
    private String room;//кабинет приема

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "schedule", orphanRemoval = true)
    private Set<Day> daySet;

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

    public Set<Day> getDaySet() {
        return daySet;
    }

    public void setDaySet(Set<Day> daySet) {
        this.daySet = daySet;
    }
}
