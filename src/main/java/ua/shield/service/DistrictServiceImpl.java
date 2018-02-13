package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.District;
import ua.shield.repository.DistrictRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("districtService")
@Transactional
public class DistrictServiceImpl implements DistrictService {
   private final DistrictRepository districtRepository;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<District> findAll() {

       return districtRepository.findAll(new Sort("name"));
     }

    @Override
    @Transactional(readOnly = true)
    public District findOneByName(String name) {
        return districtRepository.findByName(name);
    }

    @Override
    public District save(District district) {
        return districtRepository.save(district);
    }

    @Override
    @Transactional(readOnly = true)
    public District findOne(Integer id) {
       return districtRepository.findOne(id);
     }
}
