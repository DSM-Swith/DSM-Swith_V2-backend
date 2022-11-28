package com.swith.backend.global.exception;

import com.swith.backend.global.exception.error.ErrorCode;
import com.swith.backend.global.exception.error.SwithException;

public class FriendNotFoundException extends SwithException {
    public static final SwithException EXCEPTION = new FriendNotFoundException();
    private FriendNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
