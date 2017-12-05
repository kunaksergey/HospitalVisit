package ua.shield.service;

import ua.shield.entity.Doctor;

import java.util.List;


public interface DoctorService {
    List<Doctor> findAll();
    List<Doctor> findAllByNameStartsWith(String name);
    List<Doctor> findAllBySpecializationStartsWith(String name);
    Doctor save(Doctor doctor);
}
