package com.example.springbootwebservice.web;

import com.example.springbootwebservice.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 컨트롤러 (= @ResponseBody + @Controller)
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // @RequestParam - 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
