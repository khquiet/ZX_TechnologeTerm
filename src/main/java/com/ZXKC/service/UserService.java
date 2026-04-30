package com.ZXKC.service;

import com.ZXKC.domain.dto.LoginRequest;
import com.ZXKC.domain.dto.RegisterRequest;
import com.ZXKC.domain.entity.User;

public interface UserService {
    Long register(RegisterRequest request);

    String login(LoginRequest request);

    User getCurrentUser();
}
