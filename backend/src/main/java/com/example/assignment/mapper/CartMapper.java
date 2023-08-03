package com.example.assignment.mapper;


import com.example.assignment.cart.model.CartGetResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartMapper implements RowMapper<CartGetResponse> {

    @Override
    public CartGetResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

        CartGetResponse cart = new CartGetResponse();
        cart.setCartId(rs.getString("id"));
        cart.setProductId(rs.getString("product_id"));
        cart.setProductDescription(rs.getString("description"));
        cart.setProductName(rs.getString("name"));
        cart.setQuantity(rs.getInt("quantity"));
        return cart;

    }
}