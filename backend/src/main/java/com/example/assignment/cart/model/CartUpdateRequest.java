package com.example.assignment.cart.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CartUpdateRequest {

    @NotNull
    private String cartId;

    @NotNull
    private Integer quantity;

}