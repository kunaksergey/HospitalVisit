package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.*;
import ua.shield.enum_.RoleEnum;
import ua.shield.repository.DoctorRepository;
import ua.shield.repository.HospitalRepositoty;
import ua.shield.repository.SpecializationRepository;
import ua.shield.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HospitalRepositoty hospitalRepositoty;

    @Autowired
    SpecializationRepository specializationRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<User> findAll() {
         return userRepository.findByRoles(new Role(RoleEnum.ROLE_DOCTOR));
    }




    @Override
    public List<User> findAllBySpecializationStartsWith(String startStr){
        List<Specialization> specializations = specializationRepository.findAllByNameStartsWith(startStr);
        List<User> doctors = doctorRepository.findAllBySpecializationInAndRolesOrderByFullNameAsc(specializations, new Role(RoleEnum.ROLE_DOCTOR));
        return new ArrayList<>(new HashSet<>(doctors));
    }

    @Override
    public List<User> findAllBySpecializationStartsWithAndDistrict(String startStr, District district) {
        List<Specialization> specializations = specializationRepository.findAllByNameStartsWith(startStr);
        List<Hospital> hospitals = hospitalRepositoty.findAllByDistrict(district);
        List<User> doctors= doctorRepository.findAllBySpecializationInAndHospitalInAndRolesOrderByFullNameAsc(specializations,hospitals,new Role(RoleEnum.ROLE_DOCTOR));
        return new ArrayList<>(new HashSet<>(doctors));
    }

    @Override
    public List<Doctor> findAllByHospital(Hospital hospital) {
        return null;
    }

    /*
     * find doctor by started letter in his full name
     */
     @Override
    public List<User> findAllByFullNameStartsWith(String name){
        return doctorRepository.findAllByFullNameStartsWithAndRolesOrderByFullNameAsc(name,new Role(RoleEnum.ROLE_DOCTOR));
    }

    /*
     * find doctor by started letter in full name and district
     */
    @Override
    public List<User> findAllByFullNameStartsWithAndDistrict(String searchStr, District district) {
        List<Hospital> hospitals = hospitalRepositoty.findAllByDistrict(district);
        return doctorRepository.findAllByFullNameStartsWithAndHospitalAndRolesInOrderByFullNameAsc(searchStr,hospitals,new Role(RoleEnum.ROLE_DOCTOR));
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
