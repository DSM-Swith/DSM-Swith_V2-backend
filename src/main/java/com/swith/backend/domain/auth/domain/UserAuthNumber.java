package com.swith.backend.domain.auth.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class UserAuthNumber {

	@Id
	private String phoneNumber;

	private String number;

	@TimeToLive
	private long time;

	@Builder
	public UserAuthNumber(String phoneNumber, String number, long time) {
		this.phoneNumber = phoneNumber;
		this.number = number;
		this.time = 3*60*1000;
	}

}
