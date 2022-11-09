package com.swith.backend.domain.user.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class FriendId implements Serializable {

    private Long userId;

    private Long friend;
}
