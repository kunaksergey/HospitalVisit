package ua.shield.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
//@Entity
//@Table(name="doctor")
public class Doctor extends User {

    @ManyToOne
    private List<Specialization> specialization;

    @OneToOne
    private Hospital hospital;
    @Transient
    private Set<Schedule> schedules;
    @Transient
    private Set<Ticket> tickets;
    @Transient
    private List<News> newsList;

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
