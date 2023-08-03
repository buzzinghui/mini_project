package com.example.assignment.cart.controller;

import com.example.assignment.cart.model.CartCreateRequest;
import com.example.assignment.cart.model.CartDeleteRequest;
import com.example.assignment.cart.model.CartGetResponse;
import com.example.assignment.cart.model.CartUpdateRequest;
import com.example.assignment.cart.service.CartService;
import com.example.assignment.common.model.BaseResponse;
import com.example.assignment.security.privilege.Unprivileged;
import com.example.assignment.user.model.OnlineUser;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController("CartController")
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Operation(summary = "Add product to cart")
    @PostMapping("/v1")
    @Unprivileged
    public BaseResponse<Boolean> addProductToCart(@AuthenticationPrincipal OnlineUser user, @Valid @RequestBody CartCreateRequest request) {
        cartService.addProductToCart(user, request);
        return BaseResponse.success();
    }

    @Operation(summary = "Get all products in cart")
    @GetMapping("/v1")
    @Unprivileged
    public BaseResponse<List<CartGetResponse>> getProductListFromCart(@AuthenticationPrincipal OnlineUser user) {
        return BaseResponse.success(cartService.getProductListFromCart(user.getId()));
    }

    @Operation(summary = "Delete product from cart")
    @DeleteMapping("/v1")
    @Unprivileged
    public BaseResponse<Void> deleteProductFromCart(@Valid CartDeleteRequest request) {
        cartService.delete(request.getCartId());
        return BaseResponse.success();
    }

    @Operation(summary = "Update product quantity in cart")
    @PatchMapping("/v1")
    @Unprivileged
    public BaseResponse<Void> updateProduct(@Valid @RequestBody CartUpdateRequest request) {
        cartService.updateProduct(request);
        return BaseResponse.success();
    }


}