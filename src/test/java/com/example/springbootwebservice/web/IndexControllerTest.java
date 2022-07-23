package com.example.springbootwebservice.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @Test
    public void 메인페이지_로딩() {
        // when
        String body = this.testRestTemplate.getForObject("/", String.class);

        // then
        // testRestTemplate으로 호출했을 때, index.mustache에 포함되는 코드들이 있는지 확인만 하면됨
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }
}
