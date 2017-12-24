package ua.shield.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sa on 30.11.17.
 */
@Entity
@Table(name="patient")
public class Patient{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", orphanRemoval = true)
    @OrderBy(value = "fullname")
    private Set<Chield> chields;

    @OneToOne
    private User user;

    public Patient() {
    }

    public Patient(User user) {
        this.user=user;
    }

    public void addChield(Chield chield) {
        chield.setPatient(this);
        chields.add(chield);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Chield> getChields() {
        return chields;
    }

    public void setChields(Set<Chield> chields) {
        this.chields = chields;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
