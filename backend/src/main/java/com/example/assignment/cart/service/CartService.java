package com.example.assignment.cart.service;

import com.example.assignment.cart.model.CartCreateRequest;
import com.example.assignment.cart.model.CartGetResponse;
import com.example.assignment.cart.model.CartUpdateRequest;
import com.example.assignment.cart.repository.CartRepository;
import com.example.assignment.common.model.BaseResponse;
import com.example.assignment.user.model.OnlineUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public void addProductToCart(OnlineUser user, CartCreateRequest request) {
        cartRepository.addProductToCart(user.getId(), request);
    }

    public List<CartGetResponse> getProductListFromCart(String userId) {
        return cartRepository.getProductListFromCart(userId);
    }

    public void delete(String cartId) {
        cartRepository.deleteProductFromCart(cartId);
    }

    public void updateProduct(CartUpdateRequest request) {
        cartRepository.updateProductinCart(request);
    }
}
