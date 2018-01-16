package ua.shield.dto;

import ua.shield.entity.Hospital;
import ua.shield.entity.Role;
import ua.shield.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String birthday;
    private String phone;
    private String email;
    private Set<Role> roles;
    private Hospital hospital;
    private byte[] image;
    private boolean enabled;

    public UserDto() {
    }

    public User getUserFromUserDto() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setFullName(this.fullName);
        user.setPhone(this.phone);
        user.setEmail(this.email);
        user.setRoles(this.roles);
        user.setImage(this.image);
        user.setEnabled(this.enabled);

        if (this.birthday != null) {
            try {
                user.setBirthday(new SimpleDateFormat("dd-MM-yyyy").parse(this.birthday));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
