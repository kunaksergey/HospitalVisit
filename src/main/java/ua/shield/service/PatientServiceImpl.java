package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Patient;
import ua.shield.entity.Role;
import ua.shield.entity.User;
import ua.shield.enum_.RoleEnum;
import ua.shield.repository.PatientRepository;
import ua.shield.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;

@Service("patientService")
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Patient add(Patient patient) {
        patient.getUser().setRoles(new HashSet<>(Collections.singletonList(new Role(RoleEnum.ROLE_USER))));
        return patientRepository.save(patient);
    }

    @Override
    public Patient findByUser(User user) {
        return patientRepository.findByUser(user);
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient findByName(String name) {
        return patientRepository.findByUserUsername(name);
    }
}
