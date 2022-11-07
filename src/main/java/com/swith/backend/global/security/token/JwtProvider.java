package com.swith.backend.global.security.token;

import com.swith.backend.domain.refresh.domain.RefreshToken;
import com.swith.backend.domain.refresh.domain.repository.RefreshRepository;
import com.swith.backend.global.exception.TokenUnauthorizedException;
import com.swith.backend.global.security.auth.Details;
import com.swith.backend.global.security.auth.DetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${token.exp.access-token}")
    private Long accessTokenTime;

    @Value("${token.exp.refresh-token}")
    private Long refreshTokenTime;

    private final RefreshRepository refreshRepository;

    private final DetailsService detailsService;

    private byte[] encodingKey() {
        return Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8)).getBytes();
    }

    public Authentication getAuthentication(String accessToken) {

        Claims claims = parseClaims(accessToken);
        Details details = detailsService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(details, "", details.getAuthorities());
    }

    public boolean validateToken(String accessToken) {
        try {
            Jwts.parserBuilder().setSigningKey(encodingKey()).build().parseClaimsJws(accessToken);
            return true;
        } catch (Exception e) {
            throw TokenUnauthorizedException.EXCEPTION;
        }
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(encodingKey()).build().parseClaimsJws(accessToken).getBody();
        } catch (Exception e) {
            throw TokenUnauthorizedException.EXCEPTION;
        }
    }

    public String generateAccessToken(String userId) {
        return generateToken(userId, "access-token", accessTokenTime);
    }

    public String generateRefreshToken(String userId) {
        String refreshToken = generateToken(userId, "refresh-token", refreshTokenTime);
        refreshRepository.save(RefreshToken.builder()
                .userId(userId)
                .refreshTokenValue(refreshToken)
                .build());
        return refreshToken;
    }

    public String generateToken(String userId, String tokenType, Long tokenExp) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + tokenExp * 1000))
                .setIssuedAt(new Date())
                .claim("typ", tokenType)
                .signWith(SignatureAlgorithm.HS256, encodingKey())
                .setSubject(userId)
                .compact();
    }
}
