package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Hospital;
import ua.shield.repository.HospitalRepositoty;

import java.util.List;

/**
 * Created by sa on 03.12.17.
 */
@Service("hospitalService")
public class HospitalServiceImpl implements HospitalService{
    @Autowired
    HospitalRepositoty hospitalRepositoty;

    public List<Hospital> findAll(){
        return hospitalRepositoty.findAll();
    }

    @Override
    public Hospital save(Hospital hospital) {
        return hospitalRepositoty.save(hospital);
    }

    @Override
    public Hospital findOne(Integer id) {
        return hospitalRepositoty.findOne(id);
    }

    ;


}
