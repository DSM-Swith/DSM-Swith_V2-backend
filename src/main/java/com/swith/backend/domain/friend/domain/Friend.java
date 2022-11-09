package com.swith.backend.domain.user.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.swith.backend.global.entity.BaseIdEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(FriendId.class)
@Entity
public class Friend {

	@Id
	@ManyToOne
	@JoinColumn(nullable = false)
	private User userId;

	@Id
	@ManyToOne
	@JoinColumn(nullable = false)
	private User friend;

	private Boolean status;
	// 친구요청 수락시 true, 요청 대기시 false로 설정, 거절시 테이블에서 삭제
	// 요청 대기 친구와 수락된 친구가 같은 테이블에 존재

	@Builder
	public Friend(User userId, User friend) {
		this.friend = friend;
		this.userId = userId;
		this.status = false;
	}

}
