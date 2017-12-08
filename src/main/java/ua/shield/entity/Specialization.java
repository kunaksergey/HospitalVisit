package ua.shield.entity;

import javax.persistence.*;

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
}
