package com.swith.backend.domain.user.service;

import org.springframework.stereotype.Service;

import com.swith.backend.domain.auth.service.util.AuthUtil;
import com.swith.backend.domain.user.domain.User;
import com.swith.backend.domain.user.presentation.dto.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final AuthUtil authUtil;

	public UserResponse getUser() {
		User user = authUtil.getUser();
		return UserResponse.builder()
			.nickname(user.getNickname())
			.path(user.getPath())
			.build();
	}

}
