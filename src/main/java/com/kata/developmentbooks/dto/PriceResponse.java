package com.kata.developmentbooks.dto;

import java.math.BigDecimal;

public class PriceResponse {

    private BigDecimal totalPrice;

    private String currency;

    public PriceResponse() {}

    public PriceResponse(BigDecimal totalPrice, String currency) {
        this.totalPrice = totalPrice;
        this.currency = currency;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
