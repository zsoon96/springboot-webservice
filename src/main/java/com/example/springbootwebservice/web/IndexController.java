package com.example.springbootwebservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


    @GetMapping("/")
    public String index () {
        // src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리
        // = 머스테치 스타터 의존성으로 문자열 반환할 때, 앞 경로와 뒤의 파일 확장자를 자동으로 지정됨
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

}
