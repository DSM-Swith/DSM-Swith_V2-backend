package com.swith.backend.global.exception;

import com.swith.backend.global.exception.error.SwithException;
import com.swith.backend.global.exception.error.ErrorCode;

public class TokenUnauthorizedException extends SwithException {
    public static final SwithException EXCEPTION = new TokenUnauthorizedException();
    private TokenUnauthorizedException() {
        super(ErrorCode.TOKEN_UNAUTHORIZED);
    }
}
