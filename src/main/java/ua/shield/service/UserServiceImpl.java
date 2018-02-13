package ua.shield.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.Hospital;
import ua.shield.entity.Role;
import ua.shield.entity.User;
import ua.shield.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByRolesIn(Set<Role> roles) {
        return userRepository.findAllByRolesIn(roles);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllByFullNameStartWith(String nameStartWith) {
        return userRepository.findAllByFullNameStartsWith(nameStartWith);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByRoles(Role role) {
        return userRepository.findAllByRoles(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    }
