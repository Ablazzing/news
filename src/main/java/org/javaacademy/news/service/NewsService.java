package org.javaacademy.news.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.javaacademy.news.dto.CategoryDto;
import org.javaacademy.news.dto.NewsDto;
import org.javaacademy.news.dto.TodayNewsDto;
import org.javaacademy.news.entity.Category;
import org.javaacademy.news.entity.News;
import org.javaacademy.news.mapper.NewsMapper;
import org.javaacademy.news.repository.CategoryRepository;
import org.javaacademy.news.repository.NewsRepository;
import org.javaacademy.news.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Transactional
    public void createNews(NewsDto newsDto) {
        Category category = categoryRepository.findFirstByName(newsDto.getCategoryName())
                .orElse(new Category(newsDto.getCategoryName()));
        News news = newsMapper.convertToEntity(newsDto);
        news.setCategory(category);
        newsRepository.save(news);
    }

    @Transactional(readOnly = true)
    public List<NewsDto> getNewsByCategoryAndDate(String date, String category) {
        LocalDate newsDate = DateUtil.getDateFromString(date);
        return newsMapper.convertToDto(newsRepository.findAllByDateAndCategory_Name(newsDate, category));
    }

    @Transactional(readOnly = true)
    public TodayNewsDto getTodayNews() {
        List<News> news = newsRepository.findAllByDate(LocalDate.now());
        Set<String> category = news.stream().map(e -> e.getCategory().getName())
                .collect(Collectors.toSet());
        List<CategoryDto> categoryDtos = category.stream()
                .map(categoryName -> getCategoryDtoFromNews(categoryName, news))
                .collect(Collectors.toList());
        return new TodayNewsDto(categoryDtos);
    }

    private CategoryDto getCategoryDtoFromNews(String categoryName, List<News> news) {
            CategoryDto categoryDto = new CategoryDto(categoryName);
            List<NewsDto> newsDtos = news.stream()
                    .filter(e -> Objects.equals(categoryName, e.getCategory().getName()))
                    .map(newsMapper::convertToDto)
                    .toList();
            categoryDto.setNews(newsDtos);
            return categoryDto;

    }
}
