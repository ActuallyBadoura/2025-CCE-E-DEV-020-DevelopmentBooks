package com.kata.developmentbooks.controller;

import com.kata.developmentbooks.dto.BookResponse;
import com.kata.developmentbooks.model.Book;
import com.kata.developmentbooks.service.BookService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")//Todo: Restrict in production
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public BookResponse getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        return new BookResponse(allBooks);
    }
}
