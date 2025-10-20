package com.kata.developmentbooks.controller;

import com.kata.developmentbooks.dto.BasketRequest;
import com.kata.developmentbooks.dto.ErrorResponse;
import com.kata.developmentbooks.dto.PriceResponse;
import com.kata.developmentbooks.service.BookStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/store")
public class BookStoreController {

    private final BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculatePrice(@RequestBody BasketRequest basketRequest) {
        var basket = basketRequest.getBasket();
        boolean invalidBookFound = basket.stream().anyMatch(id -> id < 1 || id > 5);
        if (invalidBookFound) {
            return  ResponseEntity.badRequest().body(new ErrorResponse("Invalid input", "Book IDs must be between 1 and 5"));
        }

        BigDecimal totalPrice = bookStoreService.calculatePrice(basket);
        PriceResponse priceResponse = new PriceResponse(totalPrice, "EUR");

        return ResponseEntity.ok(priceResponse);
    }
}