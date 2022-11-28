package com.swith.backend.domain.like.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.swith.backend.domain.like.domain.Like;

public interface LikeRepository extends CrudRepository<Like, Long> {
	List<Like> findByDiaryId(Long DiaryId);
	long countByDiaryId(Long DiaryId);
	boolean existsByIdAndDiaryId(Long userId, Long diaryId);
	Optional<Like> findByDiaryIdAndUserId(Long diaryId, Long userId);
}
