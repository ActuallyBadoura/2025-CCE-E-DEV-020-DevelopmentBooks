package com.kata.developmentbooks.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    /* Price of a single book
        1. No discount for 1 book
        2. 5% discount for 2 different books
        3. 10% discount for 3 different books
        4. 20% discount for 4 different books
        5. 25% discount for 5 different books

        The calculatePrice method should compute the total price of the books in the basket,
        applying the appropriate discounts based on the number of different books purchased.
     */

    // Constants
    private static final BigDecimal BOOK_PRICE = new BigDecimal("50.00");
    private static final Map<Integer, BigDecimal> DISCOUNTS = Map.of(
            1, BigDecimal.ZERO,
            2, new BigDecimal("0.05"),
            3, new BigDecimal("0.10"),
            4, new BigDecimal("0.20"),
            5, new BigDecimal("0.25")
    );

    /* Calculate the total price of the books in the basket including discounts
       Get list of books in the basket
       Count  unique books
       Apply discount based on the number of unique books
      */
    @Override
    public BigDecimal calculatePrice(List<Integer> basket) {
        if (basket == null || basket.isEmpty()) {
            return BigDecimal.ZERO;
        }

        // Count the occurrences of each book
        Map<Integer, Integer> bookCount = countBooks(basket);

        // Calculate and return total price with discounts
        return calculateTotalPrice(bookCount);
    }

    private Map<Integer, Integer> countBooks(List<Integer> basket) {
        return basket.stream()
                .collect(java.util.stream.Collectors.toMap(
                        bookId -> bookId,
                        bookId -> 1,
                        Integer::sum
                ));
    }

    private BigDecimal calculateTotalPrice(Map<Integer, Integer> bookCount) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        while (!bookCount.isEmpty()) {
            int uniqueBooks = bookCount.size();
            BigDecimal discount = DISCOUNTS.getOrDefault(uniqueBooks, BigDecimal.ZERO);
            BigDecimal groupPrice = BOOK_PRICE
                    .multiply(BigDecimal.valueOf(uniqueBooks))
                    .multiply(BigDecimal.ONE.subtract(discount));
            totalPrice = totalPrice.add(groupPrice);

            // Decrease the count of each book in the current group
            bookCount.replaceAll((bookId, count) -> count - 1);
            // Remove books that are no longer in the basket
            bookCount.values().removeIf(count -> count <= 0);
        }

        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

}
