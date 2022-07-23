package com.example.springbootwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 빈 읽기와 생성을 모두 자동으로 설정해주는 기능
@EnableJpaAuditing // Jpa Auditing 활성화
public class SpringbootWebserviceApplication {
    // SpringApplication.run - 내장 톰캣(WAS)을 실행하므로서 언제 어디서나 같은 환경의 스프링 부트를 배포할 수 있음
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebserviceApplication.class, args);
    }

}
