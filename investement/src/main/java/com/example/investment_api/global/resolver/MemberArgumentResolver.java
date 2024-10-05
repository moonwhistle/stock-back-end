package com.example.investment_api.global.resolver;

import com.example.investment_api.global.annotation.AuthMember;
import com.example.investment_api.global.resolver.support.AuthenticationExtractor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class MemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final AuthenticationExtractor extractor;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginAnnotation = parameter.hasParameterAnnotation(AuthMember.class);
        boolean isMemberIdType = Long.class.isAssignableFrom(parameter.getParameterType());
        return isLoginAnnotation && isMemberIdType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        return extractor.extract(request);
    }
}
