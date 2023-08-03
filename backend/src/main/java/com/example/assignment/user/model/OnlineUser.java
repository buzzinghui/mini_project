package com.example.assignment.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnlineUser {

    private String id;
    private String userName;
    private String jwtToken;

    public OnlineUser(String id, String userName, String jwtToken) {
        this.id = id;
        this.userName = userName;
        this.jwtToken = jwtToken;
    }

    public OnlineUser() {

    }
}