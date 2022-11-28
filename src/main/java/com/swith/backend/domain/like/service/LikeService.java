package com.swith.backend.domain.like.service;

import org.springframework.stereotype.Service;

import com.swith.backend.domain.auth.service.util.AuthUtil;
import com.swith.backend.domain.diary.domain.Diary;
import com.swith.backend.domain.diary.domain.repository.DiaryRepository;
import com.swith.backend.domain.like.domain.Like;
import com.swith.backend.domain.like.domain.repository.LikeRepository;
import com.swith.backend.global.exception.DiaryNotFoundException;
import com.swith.backend.global.exception.MyDiaryNotLikeException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikeService {

	private final LikeRepository likeRepository;
	private final DiaryRepository diaryRepository;
	private final AuthUtil authUtil;

	public void saveOrDeleteLike(Long diaryId) {
		Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> DiaryNotFoundException.EXCEPTION);
		if(diary.getUserId().equals(authUtil.getUser().getId())) throw MyDiaryNotLikeException.EXCEPTION;

		Like like = likeRepository.findByDiaryIdAndUserId(diaryId, authUtil.getUser().getId())
			.orElse(null);

		if(like != null) {
			likeRepository.delete(like);
		} else {
			likeRepository.save(Like.builder()
				.userId(authUtil.getUser().getId())
				.diaryId(diaryId)
				.build());
		}

	}

}
