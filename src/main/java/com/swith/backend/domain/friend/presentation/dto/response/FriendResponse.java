package com.swith.backend.domain.friend.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class FriendResponse {

    //친구 신청 유저 정보
    private final Long id;
    private final String nickname;
    private final String path;

}
