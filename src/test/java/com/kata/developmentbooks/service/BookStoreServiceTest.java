package com.kata.developmentbooks.service;

import com.kata.developmentbooks.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
public class BookStoreServiceTest {

        @Autowired
        private BookStoreService bookStoreService;

        // Book IDs
        private static final int CLEAN_CODE = 1;
        private static final int CLEAN_CODER = 2;
        private static final int CLEAN_ARCHITECTURE = 3;
        private static final int TDD_BY_EXAMPLE = 4;
        private static final int LEGACY_CODE = 5;

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
}
