package com.swith.backend.domain.friend.presentation.dto.request;

import lombok.Getter;

@Getter
public class DecideRequest {

    private String userId;

    private Boolean decide;
}
