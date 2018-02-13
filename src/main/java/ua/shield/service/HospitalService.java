package ua.shield.service;

import ua.shield.entity.Doctor;
import ua.shield.entity.Hospital;

import java.util.List;


public interface HospitalService {
    List<Hospital> findAll();
    Hospital save(Hospital hospital);
    Hospital findOne(Integer id);
    List<Doctor> findAllOfHospital();
}
