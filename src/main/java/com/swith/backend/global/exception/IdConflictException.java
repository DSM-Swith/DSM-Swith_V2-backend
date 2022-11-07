package com.swith.backend.global.exception;

import com.swith.backend.global.exception.error.SwithException;
import com.swith.backend.global.exception.error.ErrorCode;

public class IdConflictException extends SwithException {
    public final static SwithException EXCEPTION = new IdConflictException();
    private IdConflictException() {
        super(ErrorCode.ID_CONFLICT);
    }
}
