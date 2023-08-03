package com.example.assignment.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
public class ProductUpdateRequest {

    @NotNull
    @Schema(description = "Product id")
    private String id;

    @Schema(description = "Product name")
    private String name;

    @Schema(description = "Product description")
    private String description;

    @Schema(description = "Product unit price")
    private BigDecimal price;

    @Schema(description = "Product stock")
    private int stock;

}
