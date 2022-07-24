package com.example.springbootwebservice.config.oauth;

import com.example.springbootwebservice.config.oauth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    // 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // @LoignUser가 붙어있고
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        // 파라미터 클래스 타입이 SessionUser일 경우
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        // true 값 반환
        return isLoginUserAnnotation && isUserClass;
    }

    // 파라미터에 전달할 객체 생성
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 세션에서 객체 가져오기
        return httpSession.getAttribute("user");
    }
}
