package com.example.assignment.cart.model;

import com.example.assignment.common.enums.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartGetResponse {

    private String cartId;
    private String productId;
    private String productName;
    private String productDescription;
    private Integer quantity;

}