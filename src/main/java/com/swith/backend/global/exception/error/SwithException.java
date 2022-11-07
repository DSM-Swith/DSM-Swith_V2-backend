package com.swith.backend.global.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SwithException extends RuntimeException{
    private final ErrorCode errorCode;
}
