package com.kata.developmentbooks.service;

import com.kata.developmentbooks.model.Book;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class BookServiceImpl implements BookService {

    List<Book> BOOKS = Arrays.asList(
            new Book(1, "Clean Code", "Robert C. Martin", new BigDecimal("50.00")),
            new Book(2, "The Clean Coder", "Robert C. Martin", new BigDecimal("50.00")),
            new Book(3, "Clean Architecture", "Robert C. Martin", new BigDecimal("50.00")),
            new Book(4, "Test-Driven Development by Example", "Kent Beck", new BigDecimal("50.00")),
            new Book(5, "Working Effectively with Legacy Code", "Michael Feathers", new BigDecimal("50.00"))
    );

    @Override
    public List<Book> getAllBooks() {
        return BOOKS;
    }
}
