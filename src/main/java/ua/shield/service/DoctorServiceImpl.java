package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Doctor;
import ua.shield.entity.Hospital;
import ua.shield.entity.Role;
import ua.shield.entity.User;
import ua.shield.enum_.RoleEnum;
import ua.shield.repository.DoctorRepository;
import ua.shield.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> findAll() {
         return userRepository.findByRoles(new Role(RoleEnum.ROLE_DOCTOR));
    }

    @Override
    public List<User> findAllByNameStartsWith(String name){
        return userRepository.findByFullNameStartsWithAndRolesOrderByFullNameAsc(name,new Role(RoleEnum.ROLE_DOCTOR));
    }

    @Override
    public List<Doctor> findAllBySpecializationStartsWith(String name){
        return null;
//        return doctorRepository.findAllBySpecializationStartsWith(name);
    }

    @Override
    public List<Doctor> findAllByHospital(Hospital hospital) {
        return null;
    }

    @Override
    public Doctor findOne(int id) {
        return null;
    }

    @Override
    public Doctor add(Doctor doctor){
       return new Doctor();
        //return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return null;
    }

    @Override
    public Doctor disable(Doctor doctor) {
        return null;
    }

    @Override
    public Doctor enable(Doctor doctor) {
        return null;
    }

    ;
}
