package com.example.assignment.product.controller;

import com.example.assignment.product.model.Product;
import com.example.assignment.product.service.ProductService;
import com.example.assignment.security.privilege.Unprivileged;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController("ProductController")
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;


    @Operation(summary = "Get list of products in the inventory")
    @GetMapping("/v1")
    @Unprivileged
    public List<Product> getProductList() {
        return productService.getProductList();
    }

}