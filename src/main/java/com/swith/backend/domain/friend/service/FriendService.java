package com.swith.backend.domain.friend.service;

import com.swith.backend.domain.auth.service.util.AuthUtil;
import com.swith.backend.domain.friend.domain.Friend;
import com.swith.backend.domain.friend.domain.repository.FriendRepository;
import com.swith.backend.domain.friend.presentation.dto.request.RequireFriend;
import com.swith.backend.domain.friend.presentation.dto.response.FriendList;
import com.swith.backend.domain.user.domain.User;
import com.swith.backend.domain.user.domain.repository.UserRepository;
import com.swith.backend.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FriendService {

    private final UserRepository userRepository;

    private final FriendRepository friendRepository;

    private final AuthUtil authUtil;

    public void requestFriend(RequireFriend require) {

        friendRepository.save(Friend.builder()
                .user(userRepository.findByUserId(authUtil.getUserId())
                        .orElseThrow(() -> {throw UserNotFoundException.EXCEPTION;
                        }))
                .friend(userRepository.findByNumberAndNickname(require.getNumber(), require.getNickName())
                        .orElseThrow(() -> {throw UserNotFoundException.EXCEPTION;}))
                .build());
    }

    public List<FriendList> friendList() {
        User user = authUtil.getUser();
        return friendRepository.findAllByUserAndStatusIsNotNull(user)
                .stream().map(
                        friend -> {
                            return FriendList.builder()
                                    .introduce(friend.getFriend().getIntroduce())
                                    .nickname(friend.getFriend().getNickname())
                                    .phoneNumber(friend.getFriend().getPhoneNumber())
                                    .status(friend.getStatus())
                                    .build();
                        }
                ).collect(Collectors.toList());
    }
}
