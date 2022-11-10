package com.swith.backend.domain.friend.service;

import com.swith.backend.domain.friend.domain.repository.FriendRepository;
import com.swith.backend.domain.friend.presentation.dto.request.RequireFriend;
import com.swith.backend.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FriendService {

    private final UserRepository userRepository;

    private final FriendRepository friendRepository;

    public void requestFriend(RequireFriend require) {

        friendRepository.save(require.toFriend(userRepository));
    }
}
