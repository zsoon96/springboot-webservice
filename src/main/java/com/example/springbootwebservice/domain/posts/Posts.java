package com.example.springbootwebservice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가 ( = public posts() {} )
@Entity // 테이블과 연결될 클래스임을 명시하는 어노테이션
public class Posts {

    @Id // 해당 테이블의 pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk의 생성 규칙 설정 / 스프링부트 2.0에서는 해당 옵션으로 설정해야 auto_increment 됨
    private Long id;

    // 기본 값 외에 추가로 변경이 필요한 옵션이 있을 때 사용하는 어노테이션
    @Column(length = 500, nullable = false) // 문자열의 사이즈를 기본값(255) > 500으로 변경 / null 값 허용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 타입을 TEXT로 변경
    private String content;

    private String author;

    // 생성자 상단에 선언 시, 생성자에 포함된 필드만 빌더에 포함하기 때문에 어느 필드에 어떤 값을 채워야할 지 명확하게 인지할 수 있다는 장점
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
