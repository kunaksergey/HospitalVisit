package ua.shield.service;

import ua.shield.entity.User;

/**
 * Created by sa on 05.12.17.
 */
public interface UserService {
    User add(User user);
    User update(User user);
    User findByUsername(String username);
}
