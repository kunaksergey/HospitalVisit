package ua.shield.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sa on 30.11.17.
 * Справочник специализаций врачей
 */
@Entity
@Table(name = "specialization")
public class Specialization {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "specialization_details",
            joinColumns = @JoinColumn(name = "SPECIALIZATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<User> user;

    public Specialization() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
