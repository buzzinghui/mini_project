package com.example.assignment.security.controller;

import com.example.assignment.common.model.BaseResponse;
import com.example.assignment.user.model.OnlineUser;
import com.example.assignment.security.model.ChangePasswordRequest;
import com.example.assignment.security.model.LoginRequest;
import com.example.assignment.security.model.LoginResponse;
import com.example.assignment.security.privilege.Unprivileged;
import com.example.assignment.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RequiredArgsConstructor
@RestController("AuthController")
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login via username or email, and password")
    @PostMapping("/v1/login")
    @Unprivileged
    public BaseResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return BaseResponse.success(authService.login(request));
    }

    @Operation(summary = "Logout customer")
    @PostMapping("/v1/logout")
    @Unprivileged
    public BaseResponse<Boolean> logout(@AuthenticationPrincipal OnlineUser customer) {
        authService.logout(customer);
        return BaseResponse.success();
    }

    @Operation(summary = "Change Password")
    @PatchMapping("/v1/password")
    @Unprivileged
    public BaseResponse<Boolean> changePassword(@AuthenticationPrincipal OnlineUser customer,
                                  @Valid @RequestBody ChangePasswordRequest request) {
        authService.changePassword(customer, request);
        return BaseResponse.success();
    }

}