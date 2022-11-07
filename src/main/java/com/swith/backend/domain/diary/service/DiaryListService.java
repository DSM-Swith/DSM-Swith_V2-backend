package com.swith.backend.domain.diary.service;

import com.swith.backend.domain.diary.domain.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.swith.backend.domain.auth.service.util.AuthUtil;
import com.swith.backend.domain.diary.domain.Diary;
import com.swith.backend.domain.diary.domain.repository.DiaryRepository;
import com.swith.backend.domain.diary.presentation.dto.response.DiaryResponses;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryListService {

	private final DiaryRepository diaryRepository;
	private final AuthUtil authUtil;

	public DiaryResponses getDiary(Pageable page) {
		return new DiaryResponses(diaryRepository.findAllByStatus(Status.PUBLIC, page)
			.map(this::ofDiaryViewResponse).toList());
	}

	public DiaryResponses getMyDiary(Pageable page) {
		return new DiaryResponses(diaryRepository.findAllByUserId(authUtil.getUser().getId(), page)
			.map(this::ofDiaryViewResponse).toList());
	}

	private DiaryResponses.DiaryResponse ofDiaryViewResponse(Diary diary) {
		return DiaryResponses.DiaryResponse.builder()
			.title(diary.getTitle())
			.path(diary.getPath())
			.build();
	}

}
