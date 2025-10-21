package com.kata.developmentbooks.model;

import java.math.BigDecimal;

public record Book(int id, String title, String author, BigDecimal price) {
}
