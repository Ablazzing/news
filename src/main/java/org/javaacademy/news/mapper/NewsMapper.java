package org.javaacademy.news.mapper;

import org.javaacademy.news.dto.NewsDto;
import org.javaacademy.news.entity.Category;
import org.javaacademy.news.entity.News;
import org.javaacademy.news.util.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(target = "date", source = "date", qualifiedByName = "getDate")
    News convertToEntity(NewsDto newsDto);

    @Named(value = "getDate")
    default LocalDate getDate(String date) {
       return DateUtil.getDateFromString(date);
    }

    @Named(value = "getStringFromDate")
    default String getStringFromDate(LocalDate date) {
        return DateUtil.getStringFromDate(date);
    }

    @Mapping(target = "date", source = "date", qualifiedByName = "getStringFromDate")
    @Mapping(target = "categoryName", source = "category.name")
    NewsDto convertToDto(News entity);

    List<NewsDto> convertToDto(List<News> news);
}
