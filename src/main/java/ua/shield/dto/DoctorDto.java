package ua.shield.dto;

import ua.shield.entity.Specialization;
import ua.shield.entity.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sa on 04.12.17.
 */
public class DoctorDto {
    private Integer id;
    private String fullName;
    private List<String> specializations;
    private String hospitalName;
    private String address;

    public DoctorDto(User user) {
        this.id=user.getId();
        this.fullName=user.getFullName();
        this.specializations=user.getSpecialization().stream().map(Specialization::getName).collect(Collectors.toList());
        hospitalName=user.getHospital().getName();
        this.address=user.getHospital().getAddress();
     }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> getSpecializations() {
        return specializations;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getAddress() {
        return address;
    }
}
