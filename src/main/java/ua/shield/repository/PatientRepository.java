package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shield.entity.Patient;
import ua.shield.entity.User;

public interface PatientRepository extends JpaRepository<Patient,Integer>{
    Patient findByUser(User user);
    Patient findByUserUsername(String userName);
}
