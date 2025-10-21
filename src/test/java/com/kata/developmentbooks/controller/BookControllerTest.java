package com.kata.developmentbooks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.developmentbooks.dto.BasketRequest;
import com.kata.developmentbooks.service.BookService;
import com.kata.developmentbooks.service.BookStoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BookControllerTest {

    @Mock
    private BookService bookService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        BookController bookController = new BookController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldReturnAllBooks_whenGetAllBooks() throws Exception {
        // Given
        var books = List.of(
                new com.kata.developmentbooks.model.Book(1, "Clean Code", "Robert C. Martin", new BigDecimal("50.00")),
                new com.kata.developmentbooks.model.Book(2, "The Clean Coder", "Robert C. Martin", new BigDecimal("45.00"))
        );

        when(bookService.getAllBooks()).thenReturn(books);

        // When / Then
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$.books[0].id").value(1))
        .andExpect(jsonPath("$.books[0].title").value("Clean Code"));
    }


}
