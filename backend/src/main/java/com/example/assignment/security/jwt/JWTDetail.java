package com.example.assignment.security.jwt;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JWTDetail {

    private String id;
    private String userName;

    public JWTDetail(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

}