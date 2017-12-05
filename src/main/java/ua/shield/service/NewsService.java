package ua.shield.service;

import ua.shield.entity.News;

import java.util.List;

/**
 * Created by sa on 03.12.17.
 */
public interface NewsService {
    List<News> findAll();
}
