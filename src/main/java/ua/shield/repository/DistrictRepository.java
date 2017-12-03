package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shield.entity.District;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer>{
    District findByName(String name);
}
