package ua.shield.service;

import ua.shield.entity.Hospital;
import ua.shield.entity.Role;
import ua.shield.entity.User;
import ua.shield.enum_.RoleEnum;
import ua.shield.exeption.UserRegistrationException;

import java.util.List;
import java.util.Set;

/**
 * Created by sa on 05.12.17.
 */
public interface UserService {
    User add(User user);
    User update(User user);
    User findByUsername(String username);
    User findOne(Integer id);
    List<User> findByRolesIn(Set<Role> roles);
    List<User> findAllByFullNameStartWith(String nameStartWith);
    List<User> findByRoles(Role role);
    List<User> findAll();
    User findByEmail(String email);

}
