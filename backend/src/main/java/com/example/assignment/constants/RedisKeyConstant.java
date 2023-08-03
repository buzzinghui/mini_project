package com.example.assignment.constants;

public class RedisKeyConstant {

    public static String generateOnlineCustomerSessionKey(String userId) {
        return "session:" + userId;
    }

}
