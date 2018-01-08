package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shield.entity.Patient;
import ua.shield.entity.User;

/**
 * Created by sa on 20.12.17.
 */
public interface PatientRepository extends JpaRepository<Patient,Integer>{
    Patient findByUser(User user);
}
