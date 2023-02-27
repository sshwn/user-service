package com.example.userservice.jpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")    // 테이블 이름 명시할 수 있다면 명시할것.
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동생성
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String userId;  // UUID로 랜덤값
    @Column(nullable = false, unique = true)
    private String encryptedPwd;
}
