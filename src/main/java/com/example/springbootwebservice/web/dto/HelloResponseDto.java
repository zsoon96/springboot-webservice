package com.example.springbootwebservice.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 모드 필드의 get 메소드 생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자 생성 (final이 없는 필드는 생성자 포함되지 않습니다.)
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
