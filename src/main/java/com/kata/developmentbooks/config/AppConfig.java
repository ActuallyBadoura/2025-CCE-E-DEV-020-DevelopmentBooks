package com.kata.developmentbooks.config;

import com.kata.developmentbooks.service.BookStoreService;
import com.kata.developmentbooks.service.BookStoreServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.kata.developmentbooks")
public class AppConfig {

    @Bean
    public BookStoreService bookStoreService() {
        return new BookStoreServiceImpl();
    }
}
