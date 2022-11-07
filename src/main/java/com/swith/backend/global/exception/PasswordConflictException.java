package com.swith.backend.global.exception;

import com.swith.backend.global.exception.error.ErrorCode;
import com.swith.backend.global.exception.error.SwithException;

public class PasswordConflictException extends SwithException {
    public final static SwithException EXCEPTION = new PasswordConflictException();
    private PasswordConflictException() {
        super(ErrorCode.PASSWORD_CONFLICT);
    }
}
