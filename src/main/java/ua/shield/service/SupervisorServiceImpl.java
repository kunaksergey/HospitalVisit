package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Supervisor;
import ua.shield.entity.User;
import ua.shield.repository.SupervisorRepository;

/**
 * Created by sa on 21.12.17.
 */
@Service("supervisorService")
public class SupervisorServiceImpl implements SupervisorService {
    @Autowired
    private SupervisorRepository supervisorRepository;

    @Override
    public Supervisor findByUser(User user) {
        return supervisorRepository.findByUser(user);
    }

    @Override
    public Supervisor findOne(Integer id) {
        return supervisorRepository.findOne(id);
    }

    @Override
    public Supervisor add(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }
}
