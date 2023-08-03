package com.example.assignment.security.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class WebSecurityContextRepository implements SecurityContextRepository {

    private final CustomAuthenticationProvider authenticationManager;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder httpRequestResponseHolder) {
        HttpServletRequest request = httpRequestResponseHolder.getRequest();
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String authToken = authHeader.substring(7);
            Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
            Authentication afterAuth = this.authenticationManager.authenticate(auth);
            log.info(afterAuth);
            return new SecurityContextImpl(afterAuth);
        }
        return SecurityContextHolder.createEmptyContext();
    }

    @Override
    public void saveContext(SecurityContext securityContext, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        // no comment
    }

    @Override
    public boolean containsContext(HttpServletRequest httpServletRequest) {
        return false;
    }
}
