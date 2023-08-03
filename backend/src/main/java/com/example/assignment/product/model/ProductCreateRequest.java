package com.example.assignment.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
public class ProductCreateRequest {

    @NotNull
    @Schema(description = "Product name")
    private String name;

    @Schema(description = "Product description")
    private String description;

    @NotNull
    @Schema(description = "Product unit price")
    private BigDecimal price;

    @NotNull
    @Schema(description = "Product stock")
    private int stock;

}
