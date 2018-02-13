package ua.shield.service;

import ua.shield.entity.News;

import java.util.List;

public interface NewsService {
    List<News> findAll();
}
