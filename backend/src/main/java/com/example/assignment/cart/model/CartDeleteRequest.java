package com.example.assignment.cart.model;

import lombok.Getter;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ParameterObject
public class CartDeleteRequest {

    @NotNull
    private String cartId;

}