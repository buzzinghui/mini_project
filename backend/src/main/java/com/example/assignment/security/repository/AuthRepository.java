package com.example.assignment.security.repository;

import com.example.assignment.common.enums.Role;
import com.example.assignment.mapper.UserRowMapper;
import com.example.assignment.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Log4j2
@CrossOrigin
public class AuthRepository {

    private final JdbcTemplate jdbcTemplate;

    public User getUser(String userName) {
        try {
            String sql = "select id, username, email_address, role, password from user where username = ?";
            return jdbcTemplate.queryForObject(sql, new Object[] { userName }, new UserRowMapper());
        } catch (Exception e) {
            log.error("no user record retrieved");
            return null;
        }
    }

    public void updateUserPass(String id, String encodedPass) {
        jdbcTemplate.update(
                "UPDATE user SET password = ?, updated_by = ?, updated = ? WHERE id = ?",
                encodedPass, id, Instant.now(), id);
    }

    public void addUser(String password) {
        jdbcTemplate.update(
                "INSERT INTO user (id, username, email_address, role, password, created_by, created) VALUES (?, ?, ?, ?, ?, ?, ?)",
                "71a6e34f-256d-4b90-bdef-0ba496333fc5", "admin", "yaiwen96@gmail.com", "ADMIN", password, "SYSTEM", Instant.now()
        );
    }
}