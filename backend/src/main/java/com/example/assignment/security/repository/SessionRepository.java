package com.example.assignment.security.repository;

import com.example.assignment.common.enums.Role;
import com.example.assignment.mapper.OnlineUserRowMapper;
import com.example.assignment.mapper.UserRowMapper;
import com.example.assignment.user.model.OnlineUser;
import com.example.assignment.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.Instant;

@RequiredArgsConstructor
@Service
@Log4j2
@CrossOrigin
public class SessionRepository {

    private final JdbcTemplate jdbcTemplate;

    public OnlineUser getSession(String key) {
        try {
            String sql = "select id, username, token from user_session where session_key = ?";
            return jdbcTemplate.queryForObject(sql, new Object[] { key }, new OnlineUserRowMapper());
        } catch (Exception e) {
            log.error("no user session retrieved");
            return null;
        }
    }
    public void insertSession(String key, OnlineUser onlineUser) {
        jdbcTemplate.update(
                "INSERT INTO user_session (session_key, id, username, token) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE token = ?",
                key, onlineUser.getId(), onlineUser.getUserName(), onlineUser.getJwtToken(), onlineUser.getJwtToken()
        );
    }

    public void deleteSession(String key) {
        jdbcTemplate.update(
                "DELETE FROM user_session WHERE session_key = ?",
                key
        );
    }
}