package com.swith.backend.domain.auth.service.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.swith.backend.domain.user.domain.User;
import com.swith.backend.domain.user.domain.repository.UserRepository;
import com.swith.backend.global.exception.TokenUnauthorizedException;
import com.swith.backend.global.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthUtil {

	private final UserRepository userRepository;

	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw TokenUnauthorizedException.EXCEPTION;
		}
		return authentication.getName();
	}

	public User getUser() {
		return userRepository.findByUserId(getUserId())
			.orElseThrow(() -> UserNotFoundException.EXCEPTION);
	}

}
