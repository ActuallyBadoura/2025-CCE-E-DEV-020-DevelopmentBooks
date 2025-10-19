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
       Apply discount based on the number of unique books.
       Optimize groups of 5 and 3 into groups of 4 and 4 for better discount
      */
    @Override
    public BigDecimal calculatePrice(List<Integer> basket) {
        if (basket == null || basket.isEmpty()) {
            return BigDecimal.ZERO;
        }

        // Count the occurrences of each book
        Map<Integer, Integer> bookCount = countBooks(basket);

        // Create groups of different books
        List<Integer> groupOfDifferentBooks = getGroupOfDifferentBooks(bookCount);

        // Calculate and return total price with discounts
        return calculateTotalPrice(groupOfDifferentBooks);
    }

    private Map<Integer, Integer> countBooks(List<Integer> basket) {
        return basket.stream()
                .collect(java.util.stream.Collectors.toMap(
                        bookId -> bookId,
                        bookId -> 1,
                        Integer::sum
                ));
    }

    private List<Integer> getGroupOfDifferentBooks(Map<Integer, Integer> bookCount) {
        List<Integer> group = new java.util.ArrayList<>();
        while (!bookCount.isEmpty()) {
            int uniqueBooks = bookCount.size();
            group.add(uniqueBooks);
            bookCount.replaceAll((bookId, count) -> count - 1);
            bookCount.values().removeIf(count -> count <= 0);
        }

        optimizeGroup(group);

        return group;
    }

    private void optimizeGroup(List<Integer> group) {
        // Optimize groups of 5 and 3 into groups of 4 and 4
        long countOfFive = group.stream().filter(size -> size == 5).count();
        long countOfThree = group.stream().filter(size -> size == 3).count();
        long pairsToOptimize = Math.min(countOfFive, countOfThree);

        for (int i = 0; i < pairsToOptimize; i++) {
            group.remove(Integer.valueOf(5));
            group.remove(Integer.valueOf(3));
            group.add(4);
            group.add(4);
        }
    }

    private BigDecimal calculateTotalPrice(List<Integer> bookCount) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for(int optimalUniqueBooks : bookCount) {
            BigDecimal discount = DISCOUNTS.getOrDefault(optimalUniqueBooks, BigDecimal.ZERO);
            BigDecimal groupPrice = BOOK_PRICE
                    .multiply(BigDecimal.valueOf(optimalUniqueBooks))
                    .multiply(BigDecimal.ONE.subtract(discount));
            totalPrice = totalPrice.add(groupPrice);
        }

        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

}
