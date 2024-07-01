package org.javaacademy.news.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private String name;
    private List<NewsDto> news;

    public CategoryDto(String name) {
        this.name = name;
    }
}
