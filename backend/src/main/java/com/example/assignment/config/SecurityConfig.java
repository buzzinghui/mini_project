package com.example.assignment.config;

import com.example.assignment.security.helper.CustomAuthenticationProvider;
import com.example.assignment.security.helper.WebSecurityContextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationProvider authProvider;
    private final WebSecurityContextRepository securityContextRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestCache().disable().cors().and().httpBasic().disable().formLogin().disable().csrf().disable()
                .logout().disable()
                .authenticationProvider(authProvider)
                .securityContext(customizer->customizer.securityContextRepository(securityContextRepository))
                .authorizeRequests()
                .antMatchers("/api/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(6);
    }

}
