package com.example.assignment.mapper;


import com.example.assignment.common.enums.Role;
import com.example.assignment.user.model.UserDetailsResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResponseRowMapper implements RowMapper<UserDetailsResponse> {

    @Override
    public UserDetailsResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDetailsResponse user = new UserDetailsResponse();
        user.setId(rs.getString("id"));
        user.setUserName(rs.getString("username"));
        user.setEmailAddress(rs.getString("email_address"));
        user.setRole((Role) rs.getObject("role"));
        return user;

    }
}