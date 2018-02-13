package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.Doctor;
import ua.shield.entity.Hospital;
import ua.shield.repository.HospitalRepositoty;

import java.util.List;

@Service("hospitalService")
@Transactional
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepositoty hospitalRepositoty;

    @Autowired
    public HospitalServiceImpl(HospitalRepositoty hospitalRepositoty) {
        this.hospitalRepositoty = hospitalRepositoty;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Hospital> findAll() {
        return hospitalRepositoty.findAll();
    }

    @Override
    public Hospital save(Hospital hospital) {
        return hospitalRepositoty.save(hospital);
    }

    @Override
    @Transactional(readOnly = true)
    public Hospital findOne(Integer id) {
        return hospitalRepositoty.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Doctor> findAllOfHospital() {

        return null;
    }
}
