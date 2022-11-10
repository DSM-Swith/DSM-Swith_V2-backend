package com.swith.backend.domain.friend.presentation.dto.response;

import lombok.Getter;

@Getter
public class DecideMessage {
    private final String message;

    public DecideMessage(String message) {
        this.message = message;
    }
}
