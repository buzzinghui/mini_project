package com.example.assignment.user.service;

import com.example.assignment.security.repository.AuthRepository;
import com.example.assignment.user.controller.UserCreateRequest;
import com.example.assignment.user.model.User;
import com.example.assignment.user.model.UserDetailsResponse;
import com.example.assignment.user.model.UserUpdateDetailsRequest;
import com.example.assignment.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class UserService {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User getUserDetails(String userName) {
        User user = authRepository.getUser(userName);
        user.setPassword(null);
        return user;
    }

    public void register(UserCreateRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userRepository.createUser(request, encodedPassword);
    }

    public void updateUserDetails(String userId, UserUpdateDetailsRequest request) {
        userRepository.updateUser(userId, request);
    }

}
