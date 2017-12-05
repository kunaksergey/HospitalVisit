package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.shield.entity.Role;

public interface RoleRrepository extends JpaRepository<Role,Integer> {
}
