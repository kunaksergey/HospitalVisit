package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shield.entity.Doctor;

import java.util.List;

@Repository
public interface DoctorRepository {
    List<Doctor> findAllByNameStartsWithOrderByNameDesc(String name);

    List<Doctor> findAllBySpecializationStartsWith(String name);
}
