package ua.shield.service;

import ua.shield.entity.Doctor;
import ua.shield.entity.Hospital;
import ua.shield.entity.User;

import java.util.List;


public interface DoctorService {
    List<User> findAll();
    List<User> findAllByNameStartsWith(String name);
    List<Doctor> findAllBySpecializationStartsWith(String name);
    List<Doctor> findAllByHospital(Hospital hospital);
    Doctor findOne(int id);
    Doctor add(Doctor doctor);
    Doctor update(Doctor doctor);
    Doctor disable(Doctor doctor);
    Doctor enable(Doctor doctor);

}
