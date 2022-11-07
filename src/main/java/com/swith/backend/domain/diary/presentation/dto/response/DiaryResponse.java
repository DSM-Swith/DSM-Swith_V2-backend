package com.swith.backend.domain.diary.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DiaryResponse {

	private final String title;
	private final String content;
	private final String path;
	private final Boolean isStatus;

	@Builder
	public DiaryResponse(String title, String content, String path, Boolean isStatus) {
		this.title = title;
		this.content = content;
		this.path = path;
		this.isStatus = isStatus;
	}

}
