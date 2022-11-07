package com.swith.backend.domain.diary.presentation.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiaryResponses {

	private final List<DiaryResponse> diaryResponses;

	@Getter
	public static class DiaryResponse {

		private final String title;
		private final String path;

		@Builder
		public DiaryResponse(String title, String path) {
			this.title = title;
			this.path = path;
		}

	}

}
