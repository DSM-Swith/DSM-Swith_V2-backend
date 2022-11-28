package com.swith.backend.domain.diary.domain.repository;

import java.util.List;

import com.swith.backend.domain.diary.domain.Diary;
import com.swith.backend.domain.diary.domain.type.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DiaryRepository extends CrudRepository<Diary, Long> {
	Page<Diary> findByStatusOrderByIdDesc(Status Status, Pageable page);
	Page<Diary> findByUserIdOrderByIdDesc(Long userId, Pageable page);
	Page<Diary> findAllByUserIdOrderByIdDesc(List<Long> userIds, Pageable page);
}
