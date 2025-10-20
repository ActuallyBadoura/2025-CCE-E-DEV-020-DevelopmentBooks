package com.kata.developmentbooks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Represents the calculated basket price response")
public class PriceResponse {

    @Schema(description = "Total calculated price of the basket", example = "150.00")
    private BigDecimal totalPrice;

    @Schema(description = "Currency of the total price", example = "EUR")
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
