package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.Supervisor;
import ua.shield.entity.User;
import ua.shield.repository.SupervisorRepository;

@Service("supervisorService")
@Transactional
public class SupervisorServiceImpl implements SupervisorService {
    private final SupervisorRepository supervisorRepository;

    @Autowired
    public SupervisorServiceImpl(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Supervisor findByUser(User user) {
        return supervisorRepository.findByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Supervisor findOne(Integer id) {
        return supervisorRepository.findOne(id);
    }

    @Override
    public Supervisor add(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }
}
