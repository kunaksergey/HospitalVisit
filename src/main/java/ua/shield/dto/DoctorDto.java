package ua.shield.dto;

import ua.shield.entity.Doctor;
import ua.shield.entity.Specialization;
import ua.shield.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sa on 04.12.17.
 */
public class DoctorDto {
    private Integer id;
    private String fullName;
    private Date birthDay;
    private String phone;
    private String email;
    private List<Specialization> specializations;
    private String hospitalName;
    private String address;
    private boolean isEnable;

    public DoctorDto() {

    }

    public DoctorDto(Doctor doctor) {
        this.id=doctor.getId();
        this.fullName=doctor.getUser().getFullName();
        this.birthDay=doctor.getUser().getBirthday();
        this.phone=doctor.getUser().getPhone();
        this.email=doctor.getUser().getEmail();
        this.specializations=doctor.getSpecializations();
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
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
}
