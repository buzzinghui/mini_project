package com.example.assignment.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Product {

    @Schema(description = "Unique identifier of product")
    private String id;

    @Schema(description = "Product name")
    private String name;

    @Schema(description = "Product description")
    private String description;

    @Schema(description = "Product price")
    private BigDecimal price;

    @Schema(description = "Product stock")
    private int stock;

    @Schema(description = "Base64 Image Thumbnail of Product")
    private String thumbnail;
}