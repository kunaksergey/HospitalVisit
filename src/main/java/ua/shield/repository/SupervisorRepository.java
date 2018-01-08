package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.shield.entity.Supervisor;
import ua.shield.entity.User;

public interface SupervisorRepository extends JpaRepository<Supervisor,Integer> {
    Supervisor findByUser(User user);
}
