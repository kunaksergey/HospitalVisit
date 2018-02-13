package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.Patient;
import ua.shield.entity.Role;
import ua.shield.entity.User;
import ua.shield.enum_.RoleEnum;
import ua.shield.repository.PatientRepository;
import ua.shield.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;

@Service("patientService")
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    private final UserRepository userRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Patient add(Patient patient) {
        patient.getUser().setRoles(new HashSet<>(Collections.singletonList(new Role(RoleEnum.ROLE_USER))));
        return patientRepository.save(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public Patient findByUser(User user) {
        return patientRepository.findByUser(user);
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public Patient findByName(String name) {
        return patientRepository.findByUserUsername(name);
    }
}
