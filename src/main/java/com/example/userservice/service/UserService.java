package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);    // user 등록

    UserDto getUserByUserId(String userId);     // userId 조회 (데이터베이스 데이터 조회 후 가공할거면 UserDto 로 받아온 데이터 그대로 사용할거면 UserEntity)

    Iterable<UserEntity> getUserByAll();    // 모든 사용자정보 조회
}
