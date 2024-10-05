package com.example.investment_api.member.infrastructure.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.investment_api.member.domain.auth.TokenProvider;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@Getter
@RequiredArgsConstructor
public class JwtTokenProvider implements TokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-period}")
    private int expirationPeriod;

    private Algorithm key;

    @PostConstruct
    private void createKey() {
        key = Algorithm.HMAC256(secret);
    }

    @Override
    public String create(Long memberId) {
        Date now = new Date();
        return JWT.create()
                .withClaim("memberId", memberId)
                .withIssuedAt(now)
                .withExpiresAt(expiredAt(now))
                .withJWTId(UUID.randomUUID().toString())
                .sign(key);
    }

    private Date expiredAt(Date createdAt) {
        return new Date(createdAt.getTime() + expirationPeriod);
    }

    @Override
    public Long extract(String token) {
        return verifyToken(token).getClaim("memberId")
                .asLong();
    }

    private DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(key)
                .build();
        return verifier.verify(token);
    }
}
