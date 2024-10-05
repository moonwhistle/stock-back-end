package com.example.investment_api.global.resolver.support;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.investment_api.global.exception.ExistTokenHeaderException;
import com.example.investment_api.global.exception.TokenTimeException;
import com.example.investment_api.global.exception.TokenVerifyException;
import com.example.investment_api.member.infrastructure.auth.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationExtractor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final int TOKEN_START_INDEX = 7;

    private final JwtTokenProvider jwtTokenProvider;

    public Long extract(HttpServletRequest request) {
        try {
            return jwtTokenProvider.extract(extractFromHeader(request));
        } catch (TokenExpiredException e) {
            throw new TokenTimeException();
        } catch (JWTVerificationException e) {
            throw new TokenVerifyException();
        }
    }

    private String extractFromHeader(HttpServletRequest request) {
        String token = Optional.ofNullable(request.getHeader(AUTHORIZATION_HEADER))
                .orElseThrow(ExistTokenHeaderException::new);

        return token.substring(TOKEN_START_INDEX);
    }
}
