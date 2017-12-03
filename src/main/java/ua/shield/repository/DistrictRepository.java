package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.shield.entity.District;

/**
 * Created by sa on 03.12.17.
 */
public interface DistrictRepository extends JpaRepository<District,Integer>{
    District findByName(String name);
}
