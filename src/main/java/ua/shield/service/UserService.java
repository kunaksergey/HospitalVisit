package ua.shield.service;

import ua.shield.entity.User;

/**
 * Created by sa on 05.12.17.
 */
public interface UserService {
    User save(User user);
    User findByLogin(String login);
}
