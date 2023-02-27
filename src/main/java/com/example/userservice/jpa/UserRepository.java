package com.example.userservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {  // <Entity 정보, 기본키의 클래스 타입>

}
