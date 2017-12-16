package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.shield.entity.Specialization;

import java.util.List;

/**
 * Created by sa on 08.12.17.
 */
public interface SpecializationRepository extends JpaRepository<Specialization,Integer>{
    List<Specialization> findAllByNameStartsWith(String searchStr);
}
