package com.example.assignment.product.controller;

import com.example.assignment.common.model.BaseResponse;
import com.example.assignment.security.privilege.Unprivileged;
import com.example.assignment.user.model.OnlineUser;
import com.example.assignment.product.model.ProductCreateRequest;
import com.example.assignment.product.model.ProductDeleteRequest;
import com.example.assignment.product.model.ProductUpdateRequest;
import com.example.assignment.product.model.ProductUploadPhotoRequest;
import com.example.assignment.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin
@RequiredArgsConstructor
@RestController("ProductAdminController")
@RequestMapping("/api/admin/product")
public class ProductAdminController {

    private final ProductService productService;

    @Operation(summary = "Create new product")
    @PostMapping("/v1")
    @Unprivileged
    public BaseResponse<Void> createNewProduct(@Valid @RequestBody ProductCreateRequest request, @AuthenticationPrincipal OnlineUser customer) {
        productService.createNewProduct(request, customer.getId());
        return BaseResponse.success();
    }

    @Operation(summary = "Update existing product")
    @PatchMapping("/v1")
    @Unprivileged
    public BaseResponse<Void> updateProduct(@Valid @RequestBody ProductUpdateRequest request, @AuthenticationPrincipal OnlineUser customer) {
        productService.updateProduct(request, customer.getId());
        return BaseResponse.success();
    }

    @Operation(summary = "Delete existing product")
    @DeleteMapping("/v1")
    @Unprivileged
    public BaseResponse<Void> deleteProduct(@Valid ProductDeleteRequest request) {
        productService.deleteProduct(request);
        return BaseResponse.success();
    }

    @Operation(summary = "Upload product image")
    @PostMapping(value = "/v1/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Unprivileged
    public BaseResponse<Void> uploadProductImage(@Valid @ModelAttribute ProductUploadPhotoRequest request, @AuthenticationPrincipal OnlineUser customer) throws IOException {
        productService.uploadProductImage(request.getId(), request.getFile(), customer.getId());
        return BaseResponse.success();
    }

}