package com.swith.backend.domain.friend.presentation.dto.request;

import com.swith.backend.domain.friend.domain.Friend;
import com.swith.backend.domain.user.domain.repository.UserRepository;
import com.swith.backend.global.exception.UserNotFoundException;
import lombok.Getter;

@Getter
public class RequireFriend {

    private String userId;

    private String friend;

    public Friend toFriend(UserRepository userRepository) {
        return Friend.builder()
                .userId(userRepository.findByUserId(this.userId)
                        .orElseThrow(() -> {throw UserNotFoundException.EXCEPTION;
                }))
                .friend(userRepository.findByUserId(this.friend)
                        .orElseThrow(() -> {throw UserNotFoundException.EXCEPTION;}))
                .build();
    }
}
