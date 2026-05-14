package com.ZXKC.controller;

import com.ZXKC.annotation.IgnoreAuth;
import com.ZXKC.domain.dto.LoginRequest;
import com.ZXKC.domain.dto.RegisterRequest;
import com.ZXKC.domain.entity.User;
import com.ZXKC.domain.result.ApiResponse;
import com.ZXKC.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @IgnoreAuth
    @PostMapping("/register")
    public ApiResponse<Map<String, Long>> register(@Valid @RequestBody RegisterRequest request) {
        Long userId = userService.register(request);
        Map<String, Long> data = new HashMap<>();
        data.put("userId", userId);
        return ApiResponse.success(data);
    }

    @IgnoreAuth
    @PostMapping("/login")
    public ApiResponse<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {
        String token = userService.login(request);
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        return ApiResponse.success(data);
    }

    @GetMapping("/me")
    public ApiResponse<User> me() {
        return ApiResponse.success(userService.getCurrentUser());
    }
}
