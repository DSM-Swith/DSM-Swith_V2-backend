package com.swith.backend.domain.diary.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.swith.backend.domain.auth.service.util.AuthUtil;
import com.swith.backend.domain.diary.domain.Diary;
import com.swith.backend.domain.diary.domain.repository.DiaryRepository;
import com.swith.backend.domain.diary.domain.type.Status;
import com.swith.backend.domain.diary.presentation.dto.response.DiaryResponses;
import com.swith.backend.domain.friend.domain.repository.FriendRepository;
import com.swith.backend.domain.like.domain.repository.LikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryListService {

	private final DiaryRepository diaryRepository;
	private final LikeRepository likeRepository;
	private final FriendRepository friendRepository;
	private final AuthUtil authUtil;

	public DiaryResponses getDiary(Pageable page) {
		Page<Diary> diary = diaryRepository.findByStatusOrderByIdDesc(Status.PUBLIC, page);

		return DiaryResponses.builder()
			.totalPage(diary.getTotalPages())
			.diaryResponses(diary.map(this::ofDiaryViewResponse).toList())
			.build();
	}

	public DiaryResponses getMyDiary(Pageable page) {
		Page<Diary> diary = diaryRepository.findByUserIdOrderByIdDesc(authUtil.getUser().getId(), page);

		return DiaryResponses.builder()
			.totalPage(diary.getTotalPages())
			.diaryResponses(diary.map(this::ofDiaryViewResponse).toList())
			.build();
	}

	public DiaryResponses getFriendDiary(Pageable page) {
		Long userId = authUtil.getUser().getId();
		List<Long> friendIds = friendRepository.findFriendIdByUserIdAndStatusIsTrue(userId);
		friendIds.addAll(friendRepository.findUserIdByFriendIdAndStatusIsTrue(userId));

		Page<Diary> diary = diaryRepository.findAllByUserIdOrderByIdDesc(friendIds, page);

		return DiaryResponses.builder()
			.totalPage(diary.getTotalPages())
			.diaryResponses(diary.map(this::ofDiaryViewResponse).toList())
			.build();
	}

	private DiaryResponses.DiaryResponse ofDiaryViewResponse(Diary diary) {
		return DiaryResponses.DiaryResponse.builder()
			.title(diary.getTitle())
			.path(diary.getPath())
			.like(likeRepository.countByDiaryId(diary.getId()))
			.build();
	}

}
