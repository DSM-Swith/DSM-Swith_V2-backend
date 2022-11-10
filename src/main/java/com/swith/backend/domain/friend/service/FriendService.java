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

@RequiredArgsConstructor
@Service
public class FriendService {

    private final UserRepository userRepository;

    private final FriendRepository friendRepository;

    private final AuthUtil authUtil;

    public void requestFriend(RequireFriend require) {

        friendRepository.save(require.toFriend(userRepository));
    }

    public List<FriendList> friendList() {
        User user = authUtil.getUser();
        List<Friend> list = friendRepository.findAllByUser(user);
        List<FriendList> friendLists = new ArrayList<>();
        for (Friend friend: list) {
            FriendList tmp = FriendList.builder()
                    .introduce(friend.getFriend().getIntroduce())
                    .nickname(friend.getFriend().getNickname())
                    .phoneNumber(friend.getFriend().getPhoneNumber())
                    .build();
            if (friend.getStatus() != null && friend.getStatus()) {
                friendLists.add(tmp);
            }
        }
        System.out.println(friendLists);
        return friendLists;
    }
}
