package com.example.springbootwebservice.web.dto;

import com.example.springbootwebservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Dto는 계층 간에 데이터 교환을 위한 객체로, 뷰를 위한 클래스임
// Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스이기에 Req / Res의 클래스와는 철저하게 분리되어야 함
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
