package com.example.assignment.cart.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CartCreateRequest {

    @NotNull
    private String productId;

    @NotNull
    private Integer quantity;

}