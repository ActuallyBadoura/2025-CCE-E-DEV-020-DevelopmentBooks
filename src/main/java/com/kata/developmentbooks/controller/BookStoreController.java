package com.kata.developmentbooks.controller;

import com.kata.developmentbooks.model.BasketRequest;
import com.kata.developmentbooks.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookStoreController {

    @Autowired
    private BookStoreService bookStoreService;

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculatePrice(@RequestBody BasketRequest basketRequest) {
        try {
            double totalPrice = bookStoreService.calculatePrice();

            return ResponseEntity.ok(Map.of(
                    "totalPrice", totalPrice,
                    "currency", "EUR"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Invalid basket data",
                    "message", e.getMessage()
            ));
        }
    }
}