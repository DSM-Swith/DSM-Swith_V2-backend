package com.swith.backend.domain.like.domain;

import javax.persistence.Entity;

import com.swith.backend.global.entity.BaseIdEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Like extends BaseIdEntity {

	private Long userId;

	private Long diaryId;

	@Builder
	public Like(Long userId, Long diaryId) {
		this.userId = userId;
		this.diaryId = diaryId;
	}

}
