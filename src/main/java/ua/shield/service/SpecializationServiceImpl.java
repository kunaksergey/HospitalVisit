package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Specialization;
import ua.shield.repository.SpecializationRepository;

import java.util.List;

/**
 * Created by sa on 08.12.17.
 */
@Service("specializationService")
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    SpecializationRepository specializationRepository;

    @Override
    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    @Override
    public Specialization save(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    @Override
    public Specialization findOne(Integer id) {
        return specializationRepository.findOne(id);
    }

 }
