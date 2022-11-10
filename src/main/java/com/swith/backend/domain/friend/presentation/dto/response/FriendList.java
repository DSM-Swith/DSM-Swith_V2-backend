package com.swith.backend.domain.friend.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendList {

    private String nickname;

    private String introduce;

    private String phoneNumber;
}
