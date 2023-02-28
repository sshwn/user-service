package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient    // MSA 유레카 서버에 등록하기 위해 추가
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		// 이 클래스안에 선언된 Bean을 메모리안에 추가한다.
		return new BCryptPasswordEncoder();
	}
}
