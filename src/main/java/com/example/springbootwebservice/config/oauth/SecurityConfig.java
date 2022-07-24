package com.example.springbootwebservice.config.oauth;

import com.example.springbootwebservice.domain.posts.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2-console 화면을 사용하기 위해 해당 옵션들 disable
        http.csrf().disable()
                .headers().frameOptions().disable()

                .and()
                // url별 권한관리 설정 옵션의 시작점 (authorizeRequests() 선언 되야만 antMatchers() 사용 가능)
                .authorizeRequests()
                // 권한 관리 대상 지정 (URL, HTTP METHOD별로 관리 가능)
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 모두 허용
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // USER 권한만 허용
                // 설정된 값 이외 나머지 URL에 대한 설정
                .anyRequest().authenticated() // 모두 인증된 사용자에게만 허용

                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 기능에 대한 설정 진입점 (로그아웃 성공 시 메인으로 이동)

                .and()
                .oauth2Login() // Oauth2 로그인 기능에 대한 설정 진입점
                .userInfoEndpoint() // Oauth2 로그인 성공 이후 사용자 정보 가져올 때의 설정
                .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록
    }
}
