package com.swith.backend.global.exception;

import com.swith.backend.global.exception.error.ErrorCode;
import com.swith.backend.global.exception.error.SwithException;

public class MyDiaryNotLikeException extends SwithException {
    public static final SwithException EXCEPTION = new MyDiaryNotLikeException();
    private MyDiaryNotLikeException() {
        super(ErrorCode.MY_DIARY_NOT_LIKE);
    }
}
