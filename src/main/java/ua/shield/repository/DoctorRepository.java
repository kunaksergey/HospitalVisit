package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shield.entity.*;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Integer>  {
    List<Doctor> findAllByUserStartsWithAndHospitalInOrderByUserAsc(User user, List<Hospital> hospitals);
    List<Doctor> findAllByUserStartsWithOrderByUserAsc(User user);
    List<Doctor> findAllBySpecializationsInOrderByUserAsc(List<Specialization> specializations);
    List<Doctor> findAllBySpecializationsInAndHospitalInOrderByUserAsc(List<Specialization> specializations, List<Hospital> hospitals);
    List<Doctor> findAllByUserInAndHospitalInOrderByUserAsc(List<User>users,List<Hospital> hospitals);
    List<Doctor> findAllByUserIn(List<User> users);
    Doctor findByUser(User user);
}
