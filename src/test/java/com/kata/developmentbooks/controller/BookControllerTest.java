package com.kata.developmentbooks.controller;

import com.kata.developmentbooks.model.Book;
import com.kata.developmentbooks.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @Mock
    private BookService bookService;

    private MockMvc mockMvc;

    List<Book> BOOKS = Arrays.asList(
            new Book(1, "Clean Code", "Robert C. Martin", new BigDecimal("50.00")),
            new Book(2, "The Clean Coder", "Robert C. Martin", new BigDecimal("50.00")),
            new Book(3, "Clean Architecture", "Robert C. Martin", new BigDecimal("50.00")),
            new Book(4, "Test-Driven Development by Example", "Kent Beck", new BigDecimal("50.00")),
            new Book(5, "Working Effectively with Legacy Code", "Michael Feathers", new BigDecimal("50.00"))
    );

    @BeforeEach
    void setup() {
        BookController bookController = new BookController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void shouldReturnAllBooks() throws Exception {
        // Given
        when(bookService.getAllBooks()).thenReturn(BOOKS);

        // When / Then
        mockMvc.perform(get("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.books.length()").value(5));
    }

}
