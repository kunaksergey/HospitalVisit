package ua.shield.service;

import ua.shield.entity.*;

import javax.print.Doc;
import java.util.List;


public interface DoctorService {
    List<Doctor> findAll();

    List<Doctor> findAllByHospital(Hospital hospital);
    List<Doctor> findAllByUserIn(List<User> users);
    List<Doctor> findAllByFullNameStartsWithAndDistrict(String searchStr, District district);
    List<Doctor> findAllByFullNameStartsWith(String searchStr);
    List<Doctor> findAllBySpecializationStartsWithAndDistrict(String searchStr, District district);
    List<Doctor> findAllBySpecializationStartsWith(String name);
    Doctor findOne(int id);
    Doctor findByUser(User user);
    Doctor add(Doctor doctor);
    Doctor update(Doctor doctor);
    Doctor disable(Doctor doctor);
    Doctor enable(Doctor doctor);
 }
