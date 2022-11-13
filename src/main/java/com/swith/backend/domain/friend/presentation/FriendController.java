package com.swith.backend.domain.friend.presentation;

import com.swith.backend.domain.friend.presentation.dto.request.DecideRequest;
import com.swith.backend.domain.friend.presentation.dto.request.RequireFriendRequest;
import com.swith.backend.domain.friend.presentation.dto.response.FriendResponse;
import com.swith.backend.domain.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PutMapping("/accept")
    public void accept(@RequestBody DecideRequest decideRequest) {
        friendService.acceptFriendRequest(decideRequest);
    }

    @DeleteMapping("/reject")
    public void reject(@RequestBody DecideRequest decideRequest) {
        friendService.rejectFriendRequest(decideRequest);
    }

    @GetMapping("/request")
    public List<FriendResponse> friendRequestList() {
        return friendService.requestFriendList();
    }
}
