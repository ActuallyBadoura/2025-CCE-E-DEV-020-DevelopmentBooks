package com.kata.developmentbooks.controller;

import com.kata.developmentbooks.dto.BasketRequest;
import com.kata.developmentbooks.dto.PriceResponse;
import com.kata.developmentbooks.service.BookStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1")
public class BookStoreController {

    private final BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @Operation(summary = "Calculate the total price of the basket with discounts applied")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Basket price calculated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid basket data")
    })
    @PostMapping("/calculate")
    public ResponseEntity<PriceResponse> calculatePrice(@RequestBody BasketRequest basketRequest) {
            var basket = basketRequest.getBasket();
            boolean invalidBookFound = basket.stream().anyMatch(id -> id < 1 || id > 5);
            if (invalidBookFound) {
                throw new IllegalArgumentException("Book IDs must be between 1 and 5");
            }

            BigDecimal totalPrice = bookStoreService.calculatePrice(basket);
            PriceResponse priceResponse = new PriceResponse(totalPrice, "EUR");

            return ResponseEntity.ok(priceResponse);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Book Store Service is running");
    }
}