package com.example.assignment.user.repository;

import com.example.assignment.common.enums.Role;
import com.example.assignment.mapper.UserResponseRowMapper;
import com.example.assignment.mapper.UserRowMapper;
import com.example.assignment.user.controller.UserCreateRequest;
import com.example.assignment.user.model.User;
import com.example.assignment.user.model.UserDetailsResponse;
import com.example.assignment.user.model.UserUpdateDetailsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Log4j2
@CrossOrigin
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserDetailsResponse getUserDetails(String userId) {
        try {
            String sql = "select id, username, email_address, role, password from user where id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[] { userId }, new UserResponseRowMapper());
        } catch (Exception e) {
            log.error("no user details are retrieved");
            return null;
        }
    }

    public void createUser(UserCreateRequest request, String encodedPassword) {
        jdbcTemplate.update(
                "INSERT INTO user (id, username, email_address, role, password, created_by, created) VALUES (UUID() , ?, ?, ?, ?, ?, ?)",
                request.getUserName(),
               request.getEmailAddress(), Role.CUSTOMER.toString(), encodedPassword, request.getUserName(), Instant.now()
        );
    }

    public void updateUser(String userId, UserUpdateDetailsRequest request) {
        jdbcTemplate.update(
                "UPDATE user SET email_address = ?, updated_by = ?, updated = ? WHERE id = ?",
                request.getEmailAddress(), userId, Instant.now(), userId);
    }
}