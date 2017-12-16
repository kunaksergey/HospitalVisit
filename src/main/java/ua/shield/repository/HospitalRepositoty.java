package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shield.entity.District;
import ua.shield.entity.Hospital;

import java.util.List;

@Repository
public interface HospitalRepositoty extends JpaRepository<Hospital,Integer> {
    List<Hospital> findAllByDistrict(District district);
}
