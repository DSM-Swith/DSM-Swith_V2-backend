package com.swith.backend.domain.friend.service;

import com.swith.backend.domain.auth.service.util.AuthUtil;
import com.swith.backend.domain.friend.domain.Friend;
import com.swith.backend.domain.friend.domain.FriendId;
import com.swith.backend.domain.friend.domain.repository.FriendRepository;
import com.swith.backend.domain.friend.presentation.dto.request.RequireFriendRequest;
import com.swith.backend.domain.friend.presentation.dto.response.FriendResponse;
import com.swith.backend.domain.user.domain.User;
import com.swith.backend.domain.user.domain.repository.UserRepository;
import com.swith.backend.global.aws.S3Util;
import com.swith.backend.global.exception.FriendNotFoundException;
import com.swith.backend.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FriendService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final AuthUtil authUtil;

    private final S3Util s3Util;

    public void requestFriend(RequireFriendRequest request) {
        friendRepository.save(Friend.builder()
                .userId(authUtil.getUser().getId())
                .friendId(userRepository.findByNumberAndNickname(request.getNumber(), request.getNickName())
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION).getId())
                .build());
    }

    public List<FriendResponse> friendList() {
        Long userId = authUtil.getUser().getId();
        List<Long> friendIds = friendRepository.findFriendIdByUserIdAndStatusIsTrue(userId);
        friendIds.addAll(friendRepository.findUserIdByFriendIdAndStatusIsTrue(userId));

        return userRepository.findAllById(friendIds).stream().map(this::ofFriendResponse).collect(Collectors.toList());
    }

    public void acceptFriendRequest(Long userId) {
        Friend friend = friendRepository.findById(new FriendId(userId, authUtil.getUser().getId()))
                .orElseThrow(() -> FriendNotFoundException.EXCEPTION);

        friendRepository.save(friend.updateStatus());
    }

    public void rejectFriendRequest(Long userId) {
        Friend friend = friendRepository.findById(new FriendId(userId, authUtil.getUser().getId()))
            .orElseThrow(() -> FriendNotFoundException.EXCEPTION);

        friendRepository.delete(friend);
    }

    public List<FriendResponse> requestFriendList() {
        List<Long> ids = friendRepository.findUserIdByFriendIdAndStatusIsFalse(authUtil.getUser());
        return userRepository.findAllById(ids).stream().map(this::ofFriendResponse).collect(Collectors.toList());
    }

    private FriendResponse ofFriendResponse(User user) {
        return FriendResponse.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .path(s3Util.getS3ObjectUrl(user.getPath()))
            .build();
    }
}
