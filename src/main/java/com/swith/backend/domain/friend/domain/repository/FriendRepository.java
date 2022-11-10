package com.swith.backend.domain.friend.domain.repository;

import com.swith.backend.domain.friend.domain.Friend;
import com.swith.backend.domain.friend.domain.FriendId;
import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends CrudRepository<Friend, FriendId> {
}
