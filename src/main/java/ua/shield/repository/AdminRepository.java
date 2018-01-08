package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.shield.entity.Admin;

/**
 * Created by sa on 22.12.17.
 */
public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
