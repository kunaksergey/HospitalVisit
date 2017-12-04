package ua.shield.service;

import ua.shield.entity.District;
import ua.shield.entity.Hospital;

import java.util.List;

/**
 * Created by sa on 03.12.17.
 */
public interface HospitalService {
    List<Hospital> findAll();
    Hospital save(Hospital hospital);
    Hospital findOne(Integer id);
}
