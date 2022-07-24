package com.example.springbootwebservice.domain.posts.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 소셜 로그인으로 반환되는 값 중 email을 통해 회원여부 확인
    Optional<User> findByEmail(String email);
}
