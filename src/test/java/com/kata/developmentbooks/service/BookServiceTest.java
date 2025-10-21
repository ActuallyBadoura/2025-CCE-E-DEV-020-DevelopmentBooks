package com.kata.developmentbooks.service;

import com.kata.developmentbooks.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookServiceTest {
    private BookService bookService;

    // Book IDs
    private static final int CLEAN_CODE = 1;
    private static final int CLEAN_CODER = 2;
    private static final int CLEAN_ARCHITECTURE = 3;
    private static final int TDD_BY_EXAMPLE = 4;
    private static final int LEGACY_CODE = 5;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl();
    }

    @Test
    void testGetAllBooks() {
        // Given

        // When
        var books = bookService.getAllBooks();

        // Then
        assert books.size() == 5;
        assert books.stream().map(Book::id).allMatch(List.of(CLEAN_CODE, CLEAN_CODER, CLEAN_ARCHITECTURE, TDD_BY_EXAMPLE, LEGACY_CODE)::contains);
    }

    @Test
    void testGetBookDetails() {
        // Given

        // When
        var books = bookService.getAllBooks();

        // Then
        var cleanCode = books.stream().filter(book -> book.id() == CLEAN_CODE).findFirst().orElse(null);
        assert cleanCode != null;
        assert cleanCode.title().equals("Clean Code");
        assert cleanCode.author().equals("Robert C. Martin");
        assert cleanCode.price().equals(new java.math.BigDecimal("50.00"));
    }

    @Test
    void testBookListIntegrity() {
        // Given

        // When
        var books = bookService.getAllBooks();

        // Then
        for (var book : books) {
            assert book.id() > 0;
            assert book.title() != null && !book.title().isEmpty();
            assert book.author() != null && !book.author().isEmpty();
            assert book.price() != null && book.price().compareTo(java.math.BigDecimal.ZERO) > 0;
        }
    }

}
