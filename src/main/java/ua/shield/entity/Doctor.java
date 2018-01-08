package ua.shield.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Entity
@Table(name="doctor")
public class Doctor{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany
    @JoinTable(name = "specialization_details",
            joinColumns = @JoinColumn(name = "DOCTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "SPECIALIZATION_ID"))
    private List<Specialization> specializations;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor", orphanRemoval = true)
    private Set<Schedule> schedules;

    @Transient
    private Set<Ticket> tickets;

    @Transient
    private List<News> newsList;

    public Doctor() {
    }

    public Doctor(User user) {
        this.user=user;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addTicket(Ticket ticket) {
        ticket.setDoctor(this);
        tickets.add(ticket);
    }
}
