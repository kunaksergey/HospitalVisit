package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.shield.entity.User;

/**
 * Created by sa on 05.12.17.
 */
public interface UserRepository extends JpaRepository<User,Integer>{
    User findByUsername(String username);
}
