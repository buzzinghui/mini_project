package com.example.assignment.cart.repository;

import com.example.assignment.cart.model.CartCreateRequest;
import com.example.assignment.cart.model.CartGetResponse;
import com.example.assignment.cart.model.CartUpdateRequest;
import com.example.assignment.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.Instant;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@Service
@Log4j2
public class CartRepository {

    private final JdbcTemplate jdbcTemplate;

    public void addProductToCart(String userId, CartCreateRequest request) {
        jdbcTemplate.update(
                "INSERT INTO cart (id, user_id, product_id, quantity, created_by, created) VALUES (UUID() , ?, ?, ?, ?, ?)",
                userId, request.getProductId(), request.getQuantity(), userId, Instant.now()
        );
    }

    public List<CartGetResponse> getProductListFromCart(String userId) {
        try {
            String sql = "select car.id, car.product_id, prod.name, prod.description, car.quantity from cart car" +
                    " inner join product prod on prod.id = car.product_id" +
                    " where car.user_id = ?";
            return jdbcTemplate.query(sql, new Object[] { userId }, new CartMapper());
        } catch (Exception e) {
            log.error("no products retrieved");
            return null;
        }
    }

    public void deleteProductFromCart(String cartId) {
        jdbcTemplate.update("DELETE FROM cart WHERE id = ?", cartId);
    }

    public void updateProductinCart(CartUpdateRequest request) {
        jdbcTemplate.update("UPDATE cart SET quantity = ? WHERE id = ?", request.getQuantity(), request.getCartId());
    }
}