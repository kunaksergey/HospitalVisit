package ua.shield.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
/**
 * Created by sa on 08.12.17.
 * Время талончика
 */
//@Entity
//@Table(name="time")
public class TimeTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name="time")
    private String time;



    public TimeTicket() {
    }

    public TimeTicket(String time) { //удалить
        this.time = time;
    }

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

}
