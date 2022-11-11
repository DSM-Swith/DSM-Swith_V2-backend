package com.swith.backend.domain.auth.service;

import javax.transaction.Transactional;

import com.swith.backend.global.exception.PhoneNumberConflictException;
import com.swith.backend.global.sms.utils.RandomCodeUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swith.backend.domain.auth.presentation.dto.request.LoginRequest;
import com.swith.backend.domain.auth.presentation.dto.request.UserInfoRequest;
import com.swith.backend.domain.auth.presentation.dto.response.TokenResponse;
import com.swith.backend.domain.refresh.domain.repository.RefreshRepository;
import com.swith.backend.domain.user.domain.User;
import com.swith.backend.domain.user.domain.repository.UserRepository;
import com.swith.backend.global.exception.IdConflictException;
import com.swith.backend.global.exception.PasswordConflictException;
import com.swith.backend.global.exception.UserNotFoundException;
import com.swith.backend.global.security.token.JwtProvider;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshRepository refreshRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signUp(UserInfoRequest userInfoRequest) {
        if(userRepository.existsByPhoneNumber(userInfoRequest.getPhoneNumber())) {
            throw PhoneNumberConflictException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .userId(userInfoRequest.getUserId())
                .password(passwordEncoder.encode(userInfoRequest.getPassword()))
                .nickname(userInfoRequest.getNickname())
                .phoneNumber(userInfoRequest.getPhoneNumber())
                .introduce(userInfoRequest.getIntroduce())
                .number("#" + RandomCodeUtil.generateRandomNumber(4))
                .build());
    }

    public TokenResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUserId(loginRequest.getUserId())
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw PasswordConflictException.EXCEPTION;
        }
        return TokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getUserId()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getUserId()))
                .build();
    }

    public void logout() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        refreshRepository.delete(refreshRepository.findById(userId)
                .orElseThrow(()-> UserNotFoundException.EXCEPTION));
    }

    public TokenResponse issuance(String token) {
        Claims claims = jwtProvider.parseClaims(token);
        User user = userRepository.findByUserId(claims.getSubject())
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        return TokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getUserId()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getUserId()))
                .build();
    }

}
