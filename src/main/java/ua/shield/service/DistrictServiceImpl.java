package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.shield.entity.District;
import ua.shield.repository.DistrictRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("districtService")
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictRepository districtRepository;


    @Override
    public List<District> findAll() {

       return districtRepository.findAll(new Sort("name"));
     }

    @Override
    public District findOneByName(String name) {
        return districtRepository.findByName(name);
    }

    @Override
    public District save(District district) {
        return districtRepository.save(district);
    }

    @Override
    public District findOne(Integer id) {
       return districtRepository.findOne(id);
     }
}
