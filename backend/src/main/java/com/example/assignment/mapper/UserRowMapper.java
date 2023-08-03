package com.example.assignment.mapper;


import com.example.assignment.common.enums.Role;
import com.example.assignment.user.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User customer = new User();
        customer.setId(rs.getString("id"));
        customer.setUserName(rs.getString("username"));
        customer.setPassword(rs.getString("password"));
        customer.setRole(Role.valueOf(rs.getString("role")));
        customer.setEmailAddress(rs.getString("email_address"));
        return customer;

    }
}