package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.shield.entity.Doctor;
import ua.shield.repository.DoctorRepository;

import java.util.List;

/**
 * Created by sa on 03.12.17.
 */
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    List<Doctor> findAllByNameStartsWith(String name){
        return doctorRepository.findAllByNameStartsWithOrderByNameDesc(name);
    }

    List<Doctor> findAllBySpecializationStartsWith(String name){
        return doctorRepository.findAllBySpecializationStartsWith(name);
    }
}
