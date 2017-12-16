package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.shield.entity.*;
import ua.shield.enum_.RoleEnum;

import java.util.List;
import java.util.Set;

/**
 * Created by sa on 05.12.17.
 */
public interface UserRepository extends JpaRepository<User,Integer>{
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByRolesIn(Set<Role> role);
    List<User> findByRoles(Role role);
    List<User> findByHospitalAndRoles(Hospital hospital, Role role);

}
