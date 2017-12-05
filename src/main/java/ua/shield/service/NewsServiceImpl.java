package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.News;
import ua.shield.repository.NewsRepository;

import java.util.ArrayList;
import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;


    @Override
    public List<News> findAll() {
        //return newsRepository.findAllOrderByDateDesc();
        return new ArrayList<>();
    }
}
