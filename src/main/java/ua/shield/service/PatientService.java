package ua.shield.service;

import ua.shield.entity.Patient;
import ua.shield.entity.User;

/**
 * Created by sa on 20.12.17.
 */
public interface PatientService {
    Patient add(Patient patient);
    Patient findByUser(User enteredUser);
    Patient update(Patient patient);
    Patient findByName(String name);
}
