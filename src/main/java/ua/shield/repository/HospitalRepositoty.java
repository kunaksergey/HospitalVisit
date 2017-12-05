package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shield.entity.Hospital;

@Repository
public interface HospitalRepositoty extends JpaRepository<Hospital,Integer> {
}
