package com.swith.backend.global.exception;

import com.swith.backend.global.exception.error.ErrorCode;
import com.swith.backend.global.exception.error.SwithException;

public class DiaryNotFoundException extends SwithException {
    public static final SwithException EXCEPTION = new DiaryNotFoundException();
    private DiaryNotFoundException() {
        super(ErrorCode.DIARY_NOT_FOUND);
    }
}
