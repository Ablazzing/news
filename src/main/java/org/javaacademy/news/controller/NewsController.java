package org.javaacademy.news.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.news.dto.NewsDto;
import org.javaacademy.news.dto.TodayNewsDto;
import org.javaacademy.news.entity.Category;
import org.javaacademy.news.entity.News;
import org.javaacademy.news.mapper.NewsMapper;
import org.javaacademy.news.repository.CategoryRepository;
import org.javaacademy.news.repository.NewsRepository;
import org.javaacademy.news.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    @PostMapping
    public ResponseEntity<?> createNews(@RequestBody NewsDto newsDto) {
        newsService.createNews(newsDto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/category/{category}")
    public List<NewsDto> findNewByCategory(@RequestParam String date, @PathVariable String category) {
        return newsService.getNewsByCategoryAndDate(date, category);
    }

    @GetMapping("/today")
    public TodayNewsDto getNewsForToday() {
        return newsService.getTodayNews();
    }
}
