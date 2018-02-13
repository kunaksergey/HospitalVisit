package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.Specialization;
import ua.shield.repository.SpecializationRepository;

import java.util.List;

@Service("specializationService")
@Transactional
public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationServiceImpl(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    @Override
    public Specialization save(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    @Override
    @Transactional(readOnly = true)
    public Specialization findOne(Integer id) {
        return specializationRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Specialization> findAllByNameStartsWith(String searchStr) {
        return specializationRepository.findAllByNameStartsWith(searchStr);
    }

}
