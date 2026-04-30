package com.ZXKC.service.impl;

import com.ZXKC.domain.context.UserContext;
import com.ZXKC.domain.dto.LoginRequest;
import com.ZXKC.domain.dto.RegisterRequest;
import com.ZXKC.domain.entity.User;
import com.ZXKC.mapper.UserMapper;
import com.ZXKC.service.UserService;
import com.ZXKC.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserMapper userMapper, JwtUtil jwtUtil) {
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Long register(RegisterRequest request) {
        User existUser = userMapper.selectByUsername(request.getUsername());
        if (existUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(md5(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setNickname(request.getNickname());
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public String login(LoginRequest request) {
        User user = userMapper.selectByUsername(request.getUsername());
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        if (!user.getPassword().equals(md5(request.getPassword()))) {
            throw new RuntimeException("用户名或密码错误");
        }
        return jwtUtil.generateToken(user.getId());
    }

    @Override
    public User getCurrentUser() {
        Long userId = UserContext.getUserId();
        if (userId == null) {
            throw new RuntimeException("用户上下文不存在");
        }
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        user.setPassword(null);
        return user;
    }

    private String md5(String rawText) {
        return DigestUtils.md5DigestAsHex(rawText.getBytes(StandardCharsets.UTF_8));
    }
}
