package com.example.assignment.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ParameterObject
public class ProductDeleteRequest {

    @NotNull
    @Schema(description = "Product Unique Identifier")
    private String id;

}
