package com.example.assignment.mapper;


import com.example.assignment.cart.model.CartGetResponse;
import com.example.assignment.user.model.OnlineUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OnlineUserRowMapper implements RowMapper<OnlineUser> {

    @Override
    public OnlineUser mapRow(ResultSet rs, int rowNum) throws SQLException {

        OnlineUser user = new OnlineUser();
        user.setUserName(rs.getString("username"));
        user.setId(rs.getString("id"));
        user.setJwtToken(rs.getString("token"));
        return user;

    }
}