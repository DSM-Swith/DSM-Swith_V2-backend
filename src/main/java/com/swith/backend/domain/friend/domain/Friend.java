package com.swith.backend.domain.friend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.swith.backend.domain.user.domain.User;

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
	private User user; // 신청자

	@Id
	@ManyToOne
	@JoinColumn(nullable = false)
	private User friend; // 친구 관계 결정자

	private Boolean status;
	// 친구요청 수락시 true, 요청 대기시 false 로 설정, 거절시 테이블에서 삭제
	// 요청 대기 친구와 수락된 친구가 같은 테이블에 존재

	@Builder
	public Friend(User user, User friend) {
		this.friend = friend;
		this.user = user;
		this.status = null;
	}

	public Friend updateStatus() {
		this.status = true;
		return this;
	}

}
