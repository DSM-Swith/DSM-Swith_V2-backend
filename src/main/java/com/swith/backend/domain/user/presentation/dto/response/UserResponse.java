package com.swith.backend.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

	private final String nickname;
	private final String path;

	@Builder
	public UserResponse(String nickname, String path) {
		this.nickname = nickname;
		this.path = path;
	}
}
