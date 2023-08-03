package com.example.assignment.security.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    @Schema(description = "JWT Token to be used in header")
    private String token;

    @Schema(description = "Username of logged in user")
    private String userName;
}