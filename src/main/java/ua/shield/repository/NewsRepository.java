package ua.shield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shield.entity.News;

import java.util.List;
@Repository
public interface NewsRepository extends JpaRepository<News,Integer>{
    //List<News> findAllOrderByDate();
}
