package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.shield.entity.Schedule;

/**
 * Created by sa on 14.12.17.
 */
public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {

}
