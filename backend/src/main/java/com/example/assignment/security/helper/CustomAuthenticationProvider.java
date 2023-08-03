package com.example.assignment.security.helper;

import com.example.assignment.constants.RedisKeyConstant;
import com.example.assignment.security.repository.SessionRepository;
import com.example.assignment.user.model.OnlineUser;
import com.example.assignment.security.jwt.JWTManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final JWTManager jwtUtil;

    private final SessionRepository sessionRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String authToken = authentication.getCredentials().toString();
            if (Boolean.FALSE.equals(jwtUtil.validateToken(authToken))) {
                return null;
            }

            Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
            List<String> roleList = claims.get("role", ArrayList.class);
            String userId = claims.get("userId", String.class);
            String key = RedisKeyConstant.generateOnlineCustomerSessionKey(userId);

            OnlineUser onlineUser = sessionRepository.getSession(key);
            log.info("test: " + onlineUser);
            if (onlineUser == null) {
                return null;
            }
            if (!onlineUser.getJwtToken().equals(authToken)) {
                return null;
            }
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (String role: roleList) {
                authorities.add(new SimpleGrantedAuthority(role));
            }

            // use the credentials and authenticate against third party system
            return new UsernamePasswordAuthenticationToken(onlineUser, null, authorities);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
