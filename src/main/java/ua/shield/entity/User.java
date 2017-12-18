package ua.shield.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.shield.enum_.RoleEnum;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Transient
    @JsonIgnore
    private String confirmPassword;

    @Column(name = "fullname")
    private String fullName;

    private String birthday;
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @OrderBy(value = "fullname")
    private Set<Chield> chields;

    @ManyToMany
    @JoinTable(name = "specialization_details",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SPECIALIZATION_ID"))
    private List<Specialization> specialization;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<Schedule> schedules;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE"))
    private Set<Role> roles;

    @OneToOne
    private Hospital hospital;
    private byte[] image;
    private boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean hasRole(RoleEnum roleEnum) {
        return roles.contains(new Role(roleEnum));
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Chield> getChields() {
        return chields;
    }

    public void setChields(Set<Chield> chields) {
        this.chields = chields;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void addChield(Chield chield) {
        chields.add(chield);
        chield.setUser(this);
    }

    public List<Specialization> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<Specialization> specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (enabled != user.enabled) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (fullName != null ? !fullName.equals(user.fullName) : user.fullName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (hospital != null ? !hospital.equals(user.hospital) : user.hospital != null) return false;
        return Arrays.equals(image, user.image);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
