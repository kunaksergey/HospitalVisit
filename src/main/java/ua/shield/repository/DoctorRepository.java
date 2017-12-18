package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shield.entity.*;

import java.util.List;

public interface DoctorRepository extends JpaRepository<User,Integer>  {
    List<User> findAllByFullNameStartsWithAndHospitalAndRolesInOrderByFullNameAsc(String name, List<Hospital> hospitals,Role role);
    List<User> findAllByFullNameStartsWithAndRolesOrderByFullNameAsc(String name,Role role);
    List<User> findAllBySpecializationInAndRolesOrderByFullNameAsc(List<Specialization> specializations, Role role);
    List<User> findAllBySpecializationInAndHospitalInAndRolesOrderByFullNameAsc(List<Specialization> specializations, List<Hospital> hospitals,Role role);
}
