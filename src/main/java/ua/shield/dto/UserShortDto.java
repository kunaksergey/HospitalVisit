package ua.shield.dto;


import ua.shield.entity.User;

import java.time.format.DateTimeFormatter;

public class UserShortDto {
    private Integer id;
    private String fullName;
    private String birthday;
    private String phone;
    private String email;

    public UserShortDto() {
    }

    public UserShortDto(User user) {
        this.id=user.getId();
        this.fullName=user.getFullName();
        this.phone=user.getPhone();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.birthday=user.getBirthday().format(formatter);
        this.email=user.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
