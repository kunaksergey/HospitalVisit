package ua.shield.repository;

import ua.shield.entity.Doctor;

import java.util.List;

/**
 * Created by sa on 03.12.17.
 */
public interface DoctorRepository {
    List<Doctor> findAllByNameStartsWithOrderByNameDesc(String name);

    List<Doctor> findAllBySpecializationStartsWith(String name);
}
