package ua.shield.service;

import ua.shield.entity.Specialization;

import java.util.List;

/**
 * Created by sa on 08.12.17.
 */

public interface SpecializationService {
    List<Specialization> findAll();
    Specialization save(Specialization specialization);
    Specialization findOne(Integer id);
}
