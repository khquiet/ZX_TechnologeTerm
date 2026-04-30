package com.ZXKC.mapper;

import com.ZXKC.domain.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int insert(User user);

    User selectByUsername(@Param("username") String username);

    User selectById(@Param("id") Long id);
}
