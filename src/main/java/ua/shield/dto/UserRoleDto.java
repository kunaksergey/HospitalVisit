package ua.shield.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.shield.entity.Chield;
import ua.shield.entity.Role;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sa on 10.12.17.
 */
public class UserRoleDto {
//    private Integer id;
//    private String username;
//    private String fullName;
//
//    private String birthday;
//    @Column(name = "phone")
//    private String phone;
//
//    @Column(name = "email")
//    private String email;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
//    @OrderBy(value = "fullname")
//    private Set<Chield> chields;
//
//    @ManyToMany
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "USER_ID"),
//            inverseJoinColumns = @JoinColumn(name = "ROLE"))
//    private Set<Role> roles;
//    @Transient
}
