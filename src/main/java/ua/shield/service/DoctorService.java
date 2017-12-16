package ua.shield.service;

import ua.shield.entity.*;

import java.util.List;


public interface DoctorService {
    List<User> findAll();

    List<Doctor> findAllByHospital(Hospital hospital);

    List<User> findAllByFullNameStartsWithAndDistrict(String searchStr, District district);
    List<User> findAllByFullNameStartsWith(String searchStr);
    List<User> findAllBySpecializationStartsWithAndDistrict(String searchStr, District district);
    List<User> findAllBySpecializationStartsWith(String name);

    Doctor findOne(int id);
    Doctor add(Doctor doctor);
    Doctor update(Doctor doctor);
    Doctor disable(Doctor doctor);
    Doctor enable(Doctor doctor);



}
