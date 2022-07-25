package com.example.springbootwebservice.config.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 스프링 시큐리티 설정 후, 테스트 할 때 @WebMvcTest에서 @SpringBootApplication / @EnableJpaAuditing을 함께 스캔하다보니 에러 발생
// 그래서 @SpringBootApplication / @EnableJpaAuditing 분리시켜줌 !
@Configuration
@EnableJpaAuditing // 이 기능을 사용하기 위해선 최소 하나의 @Entity 클래스가 필요함
public class JpaConfig {}
