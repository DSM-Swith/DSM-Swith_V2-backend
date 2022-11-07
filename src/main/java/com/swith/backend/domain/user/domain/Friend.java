package com.swith.backend.domain.user.domain;

import javax.persistence.Entity;

import com.swith.backend.global.entity.BaseIdEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Friend extends BaseIdEntity {

	private Long userId;

	private Long friend;

	private Boolean status;
	// 친구요청 수락시 true, 요청 대기시 false로 설정, 거절시 테이블에서 삭제
	// 요청 대기 친구와 수락된 친구가 같은 테이블에 존재

	public Friend(Long userId, Long friend) {
		this.friend = friend;
		this.userId = userId;
		this.status = false;
	}

}
