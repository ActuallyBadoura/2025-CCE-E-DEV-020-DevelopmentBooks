package com.kata.developmentbooks.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    @Override
    public BigDecimal calculatePrice(List<Integer> basket) {
        // Implementation logic goes here
        return BigDecimal.ZERO;
    }

}
