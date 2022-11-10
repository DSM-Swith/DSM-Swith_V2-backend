package com.swith.backend.domain.friend.presentation;

import com.swith.backend.domain.friend.domain.Friend;
import com.swith.backend.domain.friend.presentation.dto.request.RequireFriend;
import com.swith.backend.domain.friend.presentation.dto.response.FriendList;
import com.swith.backend.domain.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/friend")
@RequiredArgsConstructor
@RestController
public class FriendController {
    private final FriendService friendService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void requireFriend(@RequestBody @Valid RequireFriend require) {
        friendService.requestFriend(require);
    }

    @GetMapping("/list")
    public List<FriendList> findAllFriendList() {
        return friendService.friendList();
    }
}
