package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.shield.entity.Chield;
import ua.shield.entity.Patient;
import ua.shield.entity.User;

public interface ChieldRepository extends JpaRepository<Chield,Integer>{

}
