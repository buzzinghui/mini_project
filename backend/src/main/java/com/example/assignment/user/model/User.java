package com.example.assignment.user.model;

import com.example.assignment.common.enums.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {

    private String id;

    private String userName;

    private String emailAddress;

    private Role role;

    private String password;

}