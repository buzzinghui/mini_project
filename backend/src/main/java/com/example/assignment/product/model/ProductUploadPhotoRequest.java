package com.example.assignment.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ParameterObject
public class ProductUploadPhotoRequest {

    @NotNull
    @Schema(description = "Unique Identifier of Product")
    private String id;

    @NotNull
    @Schema(description = "File to upload")
    private MultipartFile file;

}
