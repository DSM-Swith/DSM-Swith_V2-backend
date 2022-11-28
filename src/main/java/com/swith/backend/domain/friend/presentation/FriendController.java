package com.swith.backend.domain.friend.presentation;

import com.swith.backend.domain.friend.presentation.dto.request.RequireFriendRequest;
import com.swith.backend.domain.friend.presentation.dto.response.FriendResponse;
import com.swith.backend.domain.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public void requireFriend(@RequestBody @Valid RequireFriendRequest require) {
        friendService.requestFriend(require);
    }

    @GetMapping
    public List<FriendResponse> findAllFriendList() {
        return friendService.friendList();
    }

    @PutMapping("/accept/{id}")
    public void accept(@PathVariable("id") Long userId) {
        friendService.acceptFriendRequest(userId);
    }

    @DeleteMapping("/reject/{id}")
    public void reject(@PathVariable("id") Long userId) {
        friendService.rejectFriendRequest(userId);
    }

    @GetMapping("/request")
    public List<FriendResponse> friendRequestList() {
        return friendService.requestFriendList();
    }
}
