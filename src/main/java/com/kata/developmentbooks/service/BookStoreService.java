package com.kata.developmentbooks.service;

import java.math.BigDecimal;
import java.util.List;

public interface BookStoreService {

    // Calculate the total price of the books in the basket including discounts
    BigDecimal calculatePrice(List<Integer> basket);

}
