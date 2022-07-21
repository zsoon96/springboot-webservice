package com.example.springbootwebservice.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // JUnit에 내장된 실행자 외에 다른 실행자를 실행시킬 때 (SpringRunner)
@WebMvcTest(controllers = HelloController.class) // Web(Spring MVC)에 집중할 수 있는 어노테이션 - @Controller, @ControllerAdvice 등 사용가능
public class HelloControllerTest {

        @Autowired // 빈 주입
        private MockMvc mvc; // 웹 API 테스트 시 사용

        @Test
        public void hello리턴() throws Exception {
            String hello = "hello";

            mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 GET 요청
                    .andExpect(status().isOk()) // 위 결과 검증 - http header의 status가 200(isOk())인지 검증
                    .andExpect(content().string(hello)); // 위 결과 검증 - 응답 본문 내용(리턴값)이 "hello"인지를 검증
        }

    }
