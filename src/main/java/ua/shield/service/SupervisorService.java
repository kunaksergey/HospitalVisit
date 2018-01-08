package ua.shield.service;

import ua.shield.entity.Supervisor;
import ua.shield.entity.User;

/**
 * Created by sa on 21.12.17.
 */
public interface SupervisorService {
    Supervisor findByUser(User user);
    Supervisor findOne(Integer id);
    Supervisor add(Supervisor supervisor);
}
