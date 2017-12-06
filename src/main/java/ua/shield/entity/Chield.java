package ua.shield.entity;

import javax.persistence.*;

/**
 * Created by sa on 30.11.17.
 */
@Entity
@Table(name="chield")
public class Chield {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="fullname")
    private int fullName;

    @ManyToOne
    private User parent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFullName() {
        return fullName;
    }

    public void setFullName(int fullName) {
        this.fullName = fullName;
    }
}
