package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Doctor;
import ua.shield.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {
   // @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAll() {
        //return doctorRepository.findAll();
        return new ArrayList<>();
    }

    @Override
    public List<Doctor> findAllByNameStartsWith(String name){
        return doctorRepository.findAllByNameStartsWithOrderByNameDesc(name);
    }

    @Override
    public List<Doctor> findAllBySpecializationStartsWith(String name){
        return doctorRepository.findAllBySpecializationStartsWith(name);
    }

    @Override
    public Doctor save(Doctor doctor){
       return new Doctor();
        //return doctorRepository.save(doctor);
    };
}
