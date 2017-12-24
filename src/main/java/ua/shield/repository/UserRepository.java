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
    List<User> findAllByRolesIn(Set<Role> role);
    List<User> findAllByFullNameStartsWith(String nameStartWith);
    List<User> findAllByRoles(Role role);
    List<User> findAllByFullNameStartsWithAndRoles(String nameStartWith,Role role);

}
