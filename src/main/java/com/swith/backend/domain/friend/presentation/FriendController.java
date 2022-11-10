package com.swith.backend.domain.friend.presentation;

import com.swith.backend.domain.friend.presentation.dto.request.RequireFriend;
import com.swith.backend.domain.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/friend")
@RequiredArgsConstructor
@RestController
public class FriendController {
    private final FriendService friendService;

    @PostMapping
    public void requireFriend(@RequestBody @Valid RequireFriend require) {
        friendService.requestFriend(require);
    }
}
