package com.swith.backend.domain.diary.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.swith.backend.domain.auth.service.util.AuthUtil;
import com.swith.backend.domain.diary.domain.Diary;
import com.swith.backend.domain.diary.domain.repository.DiaryRepository;
import com.swith.backend.domain.diary.presentation.dto.request.SaveDiaryRequest;
import com.swith.backend.domain.diary.presentation.dto.request.UpdateDiaryRequest;
import com.swith.backend.domain.diary.presentation.dto.response.DiaryResponse;
import com.swith.backend.global.aws.S3Util;
import com.swith.backend.global.exception.DiaryNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

	private final DiaryRepository diaryRepository;
	private final AuthUtil authUtil;
	private final S3Util s3Util;

	public Long saveDiary(SaveDiaryRequest request, MultipartFile file) {
		return diaryRepository.save(Diary.builder()
				.title(request.getTitle())
				.content(request.getContent())
				.path(s3Util.uploadImage("image", file))
				.userId(authUtil.getUser().getId())
			.build()).getId();
	}

	public Long updateDiary(Long diaryId, UpdateDiaryRequest request) {
		Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> DiaryNotFoundException.EXCEPTION);
		diary.matchUserId(authUtil.getUser().getId());

		diary.update(request.getTitle(), request.getContent(), request.getStatus());
		diaryRepository.save(diary);
		return diary.getId();
	}

	public void deleteDiary(Long diaryId) {
		Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> DiaryNotFoundException.EXCEPTION);
		diary.matchUserId(authUtil.getUser().getId());

		s3Util.delete(diary.getPath());
		diaryRepository.delete(diary);
	}

	public DiaryResponse getDiary(Long diaryId) {
		Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> DiaryNotFoundException.EXCEPTION);

		return DiaryResponse.builder()
			.title(diary.getTitle())
			.content(diary.getContent())
			.path(s3Util.getS3ObjectUrl(diary.getPath()))
			.build();
	}

}
