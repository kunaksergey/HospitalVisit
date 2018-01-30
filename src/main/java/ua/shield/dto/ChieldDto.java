package ua.shield.dto;


import ua.shield.entity.Chield;

import java.time.format.DateTimeFormatter;

public class ChieldDto {
    private Integer id;
    private String fullName;
    private Integer patientId;
    private String birthDay;

    public ChieldDto() {
    }

    public ChieldDto(Chield chield) {
        this.id=chield.getId();
        this.fullName=chield.getFullName();
        this.patientId=chield.getPatient().getId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.birthDay = chield.getBirthDay().format(formatter);
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

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
}
