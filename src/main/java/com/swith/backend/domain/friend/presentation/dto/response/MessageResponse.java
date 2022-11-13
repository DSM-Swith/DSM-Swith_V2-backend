package com.swith.backend.domain.friend.presentation.dto.response;

import lombok.Getter;

@Getter
public class MessageResponse {
    private final String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
