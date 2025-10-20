package com.kata.developmentbooks.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BasketRequest {
    private List<Integer> basket;

    public BasketRequest() {}

    @JsonCreator
    public BasketRequest(@JsonProperty("basket") List<Integer> basket) {
        this.basket = basket;
    }

    public List<Integer> getBasket() {
        return basket;
    }

    public void setBasket(List<Integer> basket) {
        this.basket = basket;
    }
}
