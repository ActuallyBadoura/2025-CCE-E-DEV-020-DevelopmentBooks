package com.kata.developmentbooks.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookStoreServiceTest {

        private BookStoreService bookStoreService;

        // Book IDs
        private static final int CLEAN_CODE = 1;
        private static final int CLEAN_CODER = 2;
        private static final int CLEAN_ARCHITECTURE = 3;
        private static final int TDD_BY_EXAMPLE = 4;
        private static final int LEGACY_CODE = 5;

        @BeforeEach
        void setUp() {
            bookStoreService = new BookStoreServiceImpl();
        }

        @Test
        void testEmptyBasket() {
            // Given
            List<Integer> basket = Arrays.asList();

            // When
            BigDecimal price = bookStoreService.calculatePrice(basket);

            // Then
            assertThat(price).isEqualTo(BigDecimal.ZERO);
        }

        @Test
        void testOneBook() {
            // Given
            List<Integer> basket = Arrays.asList(CLEAN_CODE);

            // When
            BigDecimal price = bookStoreService.calculatePrice(basket);

            // Then
            assertThat(price).isEqualTo(new BigDecimal("50.00"));
        }

    @Test
    void testTwoSameBooks() {
        // Given
        List<Integer> basket = Arrays.asList(CLEAN_CODE, CLEAN_CODE);

        // When
        BigDecimal price = bookStoreService.calculatePrice(basket);

        // Then
        assertThat(price).isEqualTo(new BigDecimal("100.00"));
    }

    @Test
    void testTwoDifferentBooks() {
        // Given - 5% discount
        List<Integer> basket = Arrays.asList(CLEAN_CODE, CLEAN_CODER);

        // When
        BigDecimal price = bookStoreService.calculatePrice(basket);

        // Then
        // 2 * 50 * 0.95 = 95
        assertThat(price).isEqualTo(new BigDecimal("95.00"));
    }

    @Test
    void testThreeDifferentBooks() {
        // Given - 10% discount
        List<Integer> basket = Arrays.asList(CLEAN_CODE, CLEAN_CODER, CLEAN_ARCHITECTURE);

        // When
        BigDecimal price = bookStoreService.calculatePrice(basket);

        // Then
        // 3 * 50 * 0.90 = 135
        assertThat(price).isEqualTo(new BigDecimal("135.00"));
    }

    @Test
    void testFourDifferentBooks() {
        // Given - 20% discount
        List<Integer> basket = Arrays.asList(
                CLEAN_CODE, CLEAN_CODER, CLEAN_ARCHITECTURE, TDD_BY_EXAMPLE
        );

        // When
        BigDecimal price = bookStoreService.calculatePrice(basket);

        // Then
        // 4 * 50 * 0.80 = 160
        assertThat(price).isEqualTo(new BigDecimal("160.00"));
    }

    @Test
    void testFiveDifferentBooks() {
        // Given - 25% discount
        List<Integer> basket = Arrays.asList(
                CLEAN_CODE, CLEAN_CODER, CLEAN_ARCHITECTURE, TDD_BY_EXAMPLE, LEGACY_CODE
        );

        // When
        BigDecimal price = bookStoreService.calculatePrice(basket);

        // Then
        // 5 * 50 * 0.75 = 187.50
        assertThat(price).isEqualTo(new BigDecimal("187.50"));
    }

    @Test
    void testMixedBasket() {
        // Given
        List<Integer> basket = Arrays.asList(
                CLEAN_CODE, CLEAN_CODE, CLEAN_CODER
        );

        // When
        BigDecimal price = bookStoreService.calculatePrice(basket);

        // Then
        // (2 * 50 * 0.95) + 50 = 95 + 50 = 145
        assertThat(price).isEqualTo(new BigDecimal("145.00"));
    }

    @Test
    void testEdgeCaseFromKata() {
        // Given - This tests is from the kata description
        List<Integer> basket = Arrays.asList(
                CLEAN_CODE, CLEAN_CODE,
                CLEAN_CODER, CLEAN_CODER,
                CLEAN_ARCHITECTURE, CLEAN_ARCHITECTURE,
                TDD_BY_EXAMPLE, LEGACY_CODE
        );

        // When
        BigDecimal price = bookStoreService.calculatePrice(basket);

        // Then
        // Correct: [1,2,3,4] + [1,2,3,5] = 160 + 160 = 320
        // Not correct: [1,2,3,4,5] + [1,2,3] = 187.50 + 135 = 322.50
        assertThat(price).isEqualTo(new BigDecimal("320.00"));
    }

    @Test
    void testNoOptimization_5Plus2() {
        // Given
        List<Integer> basket = Arrays.asList(CLEAN_CODE, CLEAN_CODER, CLEAN_ARCHITECTURE, TDD_BY_EXAMPLE, LEGACY_CODE, CLEAN_CODE, CLEAN_CODER);

        // When
        BigDecimal price = bookStoreService.calculatePrice(basket);

        // Then
        // Should stay as [1,2,3,4,5] + [1,2] = 187.50 + 95 = 282.50
        assertThat(price).isEqualTo(new BigDecimal("282.50"));
    }

    @Test
    void testMultiple_5Plus3_Optimizations() {
        // Given - Two sets of 5+3 that should become 4+4, 4+4
        List<Integer> basket = Arrays.asList(
                CLEAN_CODE, CLEAN_CODE, CLEAN_CODE, CLEAN_CODE,                                  // 4 of book 1
                CLEAN_CODER, CLEAN_CODER, CLEAN_CODER, CLEAN_CODER,                              // 4 of book 2
                CLEAN_ARCHITECTURE, CLEAN_ARCHITECTURE, CLEAN_ARCHITECTURE, CLEAN_ARCHITECTURE,  // 4 of book 3
                TDD_BY_EXAMPLE, TDD_BY_EXAMPLE,                                                  // 2 of book 4
                LEGACY_CODE, LEGACY_CODE                                                         // 2 of book 5
        );

        // When
        BigDecimal price = bookStoreService.calculatePrice(basket);

        // Then
        // Should form four groups of 4 books each
        // 4 * (4 * 50 * 0.80) = 4 * 160 = 640
        assertThat(price).isEqualTo(new BigDecimal("640.00"));
    }

    @Test
    void testPrimeNumberQuantities() {
        // Given - Testing with prime numbers: 7, 5, 3, 2, 1
        List<Integer> basket = new ArrayList<>();
        for (int i = 0; i < 7; i++) basket.add(1);
        for (int i = 0; i < 5; i++) basket.add(2);
        for (int i = 0; i < 3; i++) basket.add(3);
        for (int i = 0; i < 2; i++) basket.add(4);
        basket.add(5);

        // When
        BigDecimal price = bookStoreService.calculatePrice(basket);

        // Then
        assertThat(price).isNotNull();
        assertThat(price).isEqualTo(new BigDecimal("770.00"));
    }

    @Test
    void testLargeBasketPerformance() {
        // Given 100 of each book
        List<Integer> basket = new ArrayList<>();
        for (int book = 1; book <= 5; book++) {
            for (int i = 0; i < 100; i++) {
                basket.add(book);
            }
        }

        // When
        long startTime = System.currentTimeMillis();
        BigDecimal price = bookStoreService.calculatePrice(basket);
        long endTime = System.currentTimeMillis();

        // Then
        // Should form 100 groups of 5 books
        // 100 * (5 * 50 * 0.75) = 100 * 187.50 = 18750
        assertThat(price).isEqualTo(new BigDecimal("18750.00"));

        // Performance assertion - should complete in less than 100ms
        assertThat(endTime - startTime).isLessThan(100);
    }
}
