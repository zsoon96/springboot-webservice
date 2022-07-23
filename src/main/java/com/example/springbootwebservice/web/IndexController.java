package com.example.springbootwebservice.web;

import com.example.springbootwebservice.service.posts.PostsService;
import com.example.springbootwebservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index (Model model) { // 서버 템플릿 엔진에서 사용할 수 있는 객체 저장  / 서비스에서 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.showList());
        // src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리
        // = 머스테치 스타터 의존성으로 문자열 반환할 때, 앞 경로와 뒤의 파일 확장자를 자동으로 지정됨
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,
                              Model model) {
        PostsResponseDto dto = postsService.show(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }


}
