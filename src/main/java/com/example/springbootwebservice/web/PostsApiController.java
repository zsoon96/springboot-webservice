package com.example.springbootwebservice.web;

import com.example.springbootwebservice.service.posts.PostsService;
import com.example.springbootwebservice.web.dto.PostsResponseDto;
import com.example.springbootwebservice.web.dto.PostsSaveRequestDto;
import com.example.springbootwebservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 해당 어노테이션이 대신 생성해 줌
// 생성자를 직접 안쓰고 어노테이션을 사용한 이유는 해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 알아서 변경해주기 때문에 편리함 !!
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save (@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update (@PathVariable Long id,
                        @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto show (@PathVariable Long id) {
        return postsService.show(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
