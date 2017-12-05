package ua.shield.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="role")
public class Role implements Serializable {
    @Id
    @Column(name="ROLE")
    private String role;

    @ElementCollection(targetClass=User.class)
    @ManyToMany
    @JoinTable (name = "user_roles",
            joinColumns = @JoinColumn (name = "ROLE"),
            inverseJoinColumns =@JoinColumn(name = "USER_ID"))
    private Set<User> userSet;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        return role != null ? role.equals(role1.role) : role1.role == null;

    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }
}