package org.javaacademy.news;

import org.javaacademy.news.dto.NewsDto;
import org.javaacademy.news.service.NewsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NewsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(NewsApplication.class, args);
		NewsService newsService = context.getBean(NewsService.class);
		newsService.createNews(new NewsDto("hello", "wai wai", "sport", "21_01_2024"));
		System.out.println(newsService.getNewsByCategoryAndDate("21_01_2024", "sport"));

	}

}
