package com.kata.developmentbooks.config;

import com.kata.developmentbooks.controller.BookController;
import com.kata.developmentbooks.controller.BookStoreController;
import com.kata.developmentbooks.service.BookService;
import com.kata.developmentbooks.service.BookServiceImpl;
import com.kata.developmentbooks.service.BookStoreService;
import com.kata.developmentbooks.service.BookStoreServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.kata.developmentbooks.service")
public class AppConfig {

    @Bean
    public BookStoreService bookStoreService() {
        return new BookStoreServiceImpl();
    }

    @Bean
    public BookService bookService() {
        return new BookServiceImpl();
    }

    @Bean
    public BookStoreController bookStoreController() {
        return new BookStoreController(bookStoreService());
    }

    @Bean
    public BookController bookController() {
        return new BookController(bookService());
    }
}
