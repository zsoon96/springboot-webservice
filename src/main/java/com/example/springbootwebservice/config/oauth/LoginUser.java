package com.example.springbootwebservice.config.oauth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 세션값을 가져오는 부분의 코드를 매번 작성하지 않고, 파라미터로 바로 불러올 수 있도록 하는 어노테이션 생성

// 어노테이션이 생성될 수 있는 위치 지정
// 파라미터로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
// @interface : 이 파일을 어노테이션으로 지정하는 어노테이션
public @interface LoginUser {
}
