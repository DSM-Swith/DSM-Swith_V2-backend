package com.swith.backend.domain.friend.service;

import com.swith.backend.domain.auth.service.util.AuthUtil;
import com.swith.backend.domain.friend.domain.Friend;
import com.swith.backend.domain.friend.domain.FriendId;
import com.swith.backend.domain.friend.domain.repository.FriendRepository;
import com.swith.backend.domain.friend.presentation.dto.request.DecideRequest;
import com.swith.backend.domain.friend.presentation.dto.request.RequireFriendRequest;
import com.swith.backend.domain.friend.presentation.dto.response.FriendResponse;
import com.swith.backend.domain.user.domain.User;
import com.swith.backend.domain.user.domain.repository.UserRepository;
import com.swith.backend.global.aws.S3Util;
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

    public void requestFriend(RequireFriendRequest require) {

        friendRepository.save(Friend.builder()
                .user(authUtil.getUser())
                .friend(userRepository.findByNumberAndNickname(require.getNumber(), require.getNickName())
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION))
                .build());
    }

    public List<FriendResponse> friendList() {
        User user = authUtil.getUser();
        return friendRepository.findAllByUserAndStatusIsTrue(user)
                .stream().map(
                        friend -> {
                            return FriendResponse.builder()
                                    .nickname(friend.getFriend().getNickname())
                                    .path(s3Util.getS3ObjectUrl(friend.getFriend().getPath()))
                                    .build();
                        }
                ).collect(Collectors.toList());
    }

    public void acceptFriendRequest(DecideRequest request) {
        Long friendId = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION).getId();

        Friend friend = friendRepository.findById(new FriendId(authUtil.getUser().getId(), friendId))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        friendRepository.save(friend.updateStatus());
    }

    public void rejectFriendRequest(DecideRequest request) {
        Long friendId = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION).getId();

        Friend friend = friendRepository.findById(new FriendId(authUtil.getUser().getId(), friendId))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        friendRepository.delete(friend);
    }

    public List<FriendResponse> requestFriendList() {
        User user = authUtil.getUser();
        return friendRepository.findAllByFriendAndStatusIsFalse(user).stream()
                .map(friend -> {
                    return FriendResponse.builder()
                            .nickname(friend.getUser().getNickname())
                            .path(s3Util.getS3ObjectUrl(friend.getUser().getPath()))
                            .build();
                }).collect(Collectors.toList());
    }
}
