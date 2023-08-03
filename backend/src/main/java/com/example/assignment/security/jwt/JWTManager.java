package com.example.assignment.security.jwt;


import com.example.assignment.common.enums.Role;
import com.example.assignment.constants.SessionConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.Instant;
import java.util.*;

@Component
public class JWTManager {

    private Key key;

    @PostConstruct
    public void init() {
        String secret = "org.springframework.security.authentication.AuthenticationManager";
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public String generateJwtToken(JWTDetail jwtDetail, Role role) {
        List<String> roleList = new ArrayList<>();
        if (Role.ADMIN.equals(role)) {
            roleList.add(Role.ADMIN.toString());
        }
        roleList.add(Role.CUSTOMER.toString());

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", jwtDetail.getId());
        claims.put("userName", jwtDetail.getUserName());
        claims.put("role", roleList);
        return doGenerateToken(claims, jwtDetail.getId());
    }

    private String doGenerateToken(Map<String, Object> claims, String userId) {
        final Date createdDate = new Date();
        Instant expirationInstant = Instant.now().plus(SessionConstant.SESSION_JWT_EXPIRE_DURATION);
        Date expirationDate = Date.from(expirationInstant);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}