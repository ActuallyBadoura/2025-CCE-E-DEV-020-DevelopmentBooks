package com.kata.developmentbooks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.developmentbooks.dto.BasketRequest;
import com.kata.developmentbooks.service.BookStoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class BookStoreControllerTest {

    @Mock
    private BookStoreService bookStoreService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        BookStoreController bookStoreController = new BookStoreController(bookStoreService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookStoreController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldReturnTotalPrice_whenBasketIsValid() throws Exception {
        // Given
        BasketRequest basketRequest = new BasketRequest();
        basketRequest.setBasket(List.of(1, 2, 3));

        when(bookStoreService.calculatePrice(List.of(1, 2, 3)))
                .thenReturn(new BigDecimal("135.00"));

        // When / Then
        mockMvc.perform(post("/api/v1/store/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(basketRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(135.00))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

}
