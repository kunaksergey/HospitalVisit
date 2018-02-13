package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.News;
import ua.shield.repository.NewsRepository;

import java.util.ArrayList;
import java.util.List;

@Service("newsService")
@Transactional
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    @Override
    public List<News> findAll() {
        //return newsRepository.findAllOrderByDateDesc();
        return new ArrayList<>();
    }
}
