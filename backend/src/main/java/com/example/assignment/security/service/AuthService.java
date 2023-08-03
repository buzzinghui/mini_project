package com.example.assignment.security.service;

import com.example.assignment.common.enums.ApplicationResponseCode;
import com.example.assignment.common.exception.ApplicationException;
import com.example.assignment.common.exception.UnauthorizedException;
import com.example.assignment.constants.RedisKeyConstant;
import com.example.assignment.constants.SessionConstant;
import com.example.assignment.security.repository.SessionRepository;
import com.example.assignment.user.model.OnlineUser;
import com.example.assignment.user.model.User;
import com.example.assignment.security.jwt.JWTDetail;
import com.example.assignment.security.jwt.JWTManager;
import com.example.assignment.security.repository.AuthRepository;
import com.example.assignment.security.model.ChangePasswordRequest;
import com.example.assignment.security.model.LoginRequest;
import com.example.assignment.security.model.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class AuthService {

    private final AuthRepository authRepository;
    private final SessionRepository sessionRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTManager jwtManager;

    public LoginResponse login(LoginRequest request) {
        User user = authRepository.getUser(request.getUserName());
        if (user != null) {
            // verify user password
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new ApplicationException(ApplicationResponseCode.APPLICATION_INVALID_PASSWORD);
            }

            // create customer login session
            String jwtToken = createCustomerSession(user);

            LoginResponse response = new LoginResponse();
            response.setToken(jwtToken);
            response.setUserName(user.getUserName());
            return response;

        } else {
            throw new UnauthorizedException(ApplicationResponseCode.APPLICATION_INVALID_LOGIN);
        }
    }

    public void changePassword(OnlineUser customer, ChangePasswordRequest request) {
        User user = authRepository.getUser(customer.getUserName());
        if (user != null) {
            // verify user password
            if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
                throw new UnauthorizedException(ApplicationResponseCode.APPLICATION_INVALID_PASSWORD);
            }

            // verify new password and confirm password must be the same
            if (!request.getConfirmPassword().equals(request.getNewPassword())) {
                throw new UnauthorizedException(ApplicationResponseCode.APPLICATION_INVALID_NEW_CONFIRM_PASSWORD);
            }

            // update password if all validation passed
            String encodedPass = passwordEncoder.encode(request.getNewPassword());
            authRepository.updateUserPass(customer.getId(), encodedPass);

        } else {
            throw new UnauthorizedException(ApplicationResponseCode.APPLICATION_INVALID_LOGIN);
        }
    }

    private String createCustomerSession(User user) {
        JWTDetail jwtDetail = new JWTDetail(user.getId(), user.getUserName());
        String jwtToken  = jwtManager.generateJwtToken(jwtDetail, user.getRole());

        OnlineUser onlineUser = new OnlineUser(user.getId(), user.getUserName(), jwtToken);
        String key = RedisKeyConstant.generateOnlineCustomerSessionKey(user.getId());

        sessionRepository.insertSession(key, onlineUser);
        return jwtToken;
    }

    public void logout(OnlineUser customer) {
        String key = RedisKeyConstant.generateOnlineCustomerSessionKey(customer.getId());
        sessionRepository.deleteSession(key);
    }

}
