package com.example.springbootwebservice.web;

import com.example.springbootwebservice.config.oauth.LoginUser;
import com.example.springbootwebservice.config.oauth.dto.SessionUser;
import com.example.springbootwebservice.service.posts.PostsService;
import com.example.springbootwebservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    // 서버 템플릿 엔진에서 사용할 수 있는 객체 저장  / 서비스에서 가져온 결과를 posts로 index.mustache에 전달
    // @LoginUser를 통해 세션정보 파라미터로 가져올 수 있음
    public String index (Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.showList());

        // customOAuth2UserService에서 로그인 성공 시 세션에 session user를 저장하도록 구성
        // 즉 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있음
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        // user가 있으면 값을 담아서 index에 넘겨줌
        if ( user != null ) {
            model.addAttribute("userName", user.getName());
        }
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
        // model.addAttribute( 머스테치 속성 이름(value)에, 전달할 데이터 )
        model.addAttribute("post", dto);
        return "posts-update";
    }

}
