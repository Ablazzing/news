package org.javaacademy.news.repository;

import org.javaacademy.news.dto.NewsDto;
import org.javaacademy.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByDateAndCategory_Name(LocalDate newsDate, String category);

    List<News> findAllByDate(LocalDate date);
}
