package com.kata.developmentbooks.service;

import com.kata.developmentbooks.config.AppConfig;
import com.kata.developmentbooks.config.WebConfig;
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
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
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
}
