package com.swith.backend.domain.friend.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode // 필수
@Getter
@NoArgsConstructor // 필수
@AllArgsConstructor
public class FriendId implements Serializable {

    private Long userId;

    private Long friend;
}
