package com.example.assignment.user.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCreateRequest {

    @NotNull
    private String userName;

    @NotNull
    private String emailAddress;

    @NotNull
    private String password;

}