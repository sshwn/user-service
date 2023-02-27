package com.example.userservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;

    // 중간단계 class로 이동될때 사용하겠다.
    private String userId;
    private Date createdAt;

    private String encryptedPwd;
}
