package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ua.shield.entity.*;
import ua.shield.enum_.RoleEnum;
import ua.shield.repository.DoctorRepository;
import ua.shield.repository.HospitalRepositoty;
import ua.shield.repository.SpecializationRepository;
import ua.shield.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HospitalRepositoty hospitalRepositoty;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAll() {
         return doctorRepository.findAll();
    }


    @Override
    public List<Doctor> findAllBySpecializationStartsWith(String startStr){
        List<Specialization> specializations = specializationRepository.findAllByNameStartsWith(startStr);
        List<Doctor> doctors = doctorRepository.findAllBySpecializationsInOrderByUserAsc(specializations);
        return new ArrayList<>(new HashSet<>(doctors));
    }

    @Override
    public List<Doctor> findAllBySpecializationStartsWithAndDistrict(String startStr, District district) {
        List<Specialization> specializations = specializationRepository.findAllByNameStartsWith(startStr);
        List<Hospital> hospitals = hospitalRepositoty.findAllByDistrict(district);
        List<Doctor> doctors= doctorRepository.findAllBySpecializationsInAndHospitalInOrderByUserAsc(specializations,hospitals);
        return new ArrayList<>(new HashSet<>(doctors));
    }

    @Override
    public List<Doctor> findAllByHospital(Hospital hospital) {
        return null;
    }

    @Override
    public List<Doctor> findAllByUserIn(List<User> users) {
        return doctorRepository.findAllByUserIn(users);
    }

    /*
     * find doctor by started letter in his full name
     */
     @Override
    public List<Doctor> findAllByFullNameStartsWith(String nameStartWith){
        List<User> users=userRepository.findAllByFullNameStartsWithAndRoles(nameStartWith,new Role(RoleEnum.ROLE_DOCTOR));
        return doctorRepository.findAllByUserIn(users);
    }

    /*
     * find doctor by started letter in full name and district
     */
    @Override
    public List<Doctor> findAllByFullNameStartsWithAndDistrict(String nameStartWith, District district) {
        List<Hospital> hospitals = hospitalRepositoty.findAllByDistrict(district);
        List<User> users=userRepository.findAllByFullNameStartsWithAndRoles(nameStartWith,new Role(RoleEnum.ROLE_DOCTOR));
        return doctorRepository.findAllByUserInAndHospitalInOrderByUserAsc(users,hospitals);
    }

    @Override
    public Doctor findOne(int id) {
        return null;
    }

    @Override
    public Doctor findByUser(User user) {
        return doctorRepository.findByUser(user);
    }

    @Override
    public Doctor add(Doctor doctor){
        doctor.getUser().setRoles(new HashSet<>(Collections.singletonList(new Role(RoleEnum.ROLE_DOCTOR))));
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return doctorRepository.save(doctor);
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
