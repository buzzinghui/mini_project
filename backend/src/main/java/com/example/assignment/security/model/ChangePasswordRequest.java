package com.example.assignment.security.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChangePasswordRequest {

    @NotNull
    @Schema(description = "Old Password")
    private String oldPassword;

    @NotNull
    @Schema(description = "New Password")
    private String newPassword;

    @NotNull
    @Schema(description = "Confirmed Password")
    private String confirmPassword;

}