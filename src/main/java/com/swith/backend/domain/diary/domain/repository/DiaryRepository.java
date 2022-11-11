package com.swith.backend.domain.diary.domain.repository;

import com.swith.backend.domain.diary.domain.Diary;

import com.swith.backend.domain.diary.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DiaryRepository extends CrudRepository<Diary, Long> {
	Page<Diary> findAllByStatus(Status Status, Pageable page);
	Page<Diary> findAllByUserId(Long userId, Pageable page);
}
