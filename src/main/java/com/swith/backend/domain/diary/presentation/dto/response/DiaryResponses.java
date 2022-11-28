package com.swith.backend.domain.diary.presentation.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class DiaryResponses {

	private long totalPage;
	private final List<DiaryResponse> diaryResponses;

	@Getter
	public static class DiaryResponse {

		private final String title;
		private final long like;
		private final String path;

		@Builder
		public DiaryResponse(String title, String path, long like) {
			this.title = title;
			this.like = like;
			this.path = path;
		}

	}

}
