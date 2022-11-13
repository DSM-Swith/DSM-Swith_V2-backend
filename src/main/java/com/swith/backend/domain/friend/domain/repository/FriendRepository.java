package com.swith.backend.domain.friend.domain.repository;

import com.swith.backend.domain.friend.domain.Friend;
import com.swith.backend.domain.friend.domain.FriendId;
import com.swith.backend.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, FriendId> {
    List<Friend> findAllByUserAndStatusIsTrue(User user);
    List<Friend> findAllByFriendAndStatusIsFalse(User friend);
}
