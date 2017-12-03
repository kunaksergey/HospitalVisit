package ua.shield.service;

import ua.shield.entity.District;

import java.util.List;

/**
 * Created by sa on 03.12.17.
 */
public interface DistrictService {
    List<District> findAll();
    District findOneByName(String name);
    District save(District district);
    District findOne(Integer id);
}
