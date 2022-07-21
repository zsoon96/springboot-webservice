package com.example.springbootwebservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 컨트롤러 (= @ResponseBody + @Controller)
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
