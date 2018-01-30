package ua.shield.dto;

import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;
import ua.shield.entity.Specialization;
import ua.shield.entity.User;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DoctorDto {
    private Integer id;
    private Integer userId;
    private String fullName;
    private String birthDay;
    private String phone;
    private String email;
    private List<Specialization> specializations;
    private Set<Schedule> schedules;
    private String hospitalName;
    private String address;
    private boolean isEnable;

    public DoctorDto() {

    }

    public DoctorDto(Doctor doctor) {
        this.id=doctor.getId();
        this.userId=doctor.getUser().getId();
        this.fullName=doctor.getUser().getFullName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.birthDay=doctor.getUser().getBirthday().format(formatter);
        this.phone=doctor.getUser().getPhone();
        this.email=doctor.getUser().getEmail();
        this.specializations=doctor.getSpecializations();
        this.schedules=doctor.getSchedules();
        this.hospitalName=doctor.getHospital().getName();
        this.address=doctor.getHospital().getAddress();
        this.isEnable=doctor.getUser().isEnabled();
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
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

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
