package com.example.assignment.user.controller;

import com.example.assignment.common.model.BaseResponse;
import com.example.assignment.security.privilege.Unprivileged;
import com.example.assignment.user.model.OnlineUser;
import com.example.assignment.user.model.User;
import com.example.assignment.user.model.UserDetailsResponse;
import com.example.assignment.user.model.UserUpdateDetailsRequest;
import com.example.assignment.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController("UserController")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "New user registration")
    @PostMapping("/v1/register")
    @Unprivileged
    public BaseResponse<Boolean> registerUser(@Valid @RequestBody UserCreateRequest request) {
        userService.register(request);
        return BaseResponse.success();
    }

    @Operation(summary = "Get user details")
    @GetMapping("/v1/details")
    @Unprivileged
    public BaseResponse<User> getUserDetails(@AuthenticationPrincipal OnlineUser user) {
        return BaseResponse.success(userService.getUserDetails(user.getUserName()));
    }

    @Operation(summary = "uPDATE user details")
    @PatchMapping("/v1/details")
    @Unprivileged
    public BaseResponse<Void> updateUserDetails(@AuthenticationPrincipal OnlineUser user, @Valid @RequestBody UserUpdateDetailsRequest request) {
        userService.updateUserDetails(user.getId(), request);
        return BaseResponse.success();
    }

}