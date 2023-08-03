package com.example.assignment.security.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest {

    @NotNull
    @Schema(description = "username for login")
    private String userName;

    @NotNull
    @Schema(description = "password for login")
    private String password;

}