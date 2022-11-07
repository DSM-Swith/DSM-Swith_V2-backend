package com.swith.backend.global.exception;

import com.swith.backend.global.exception.error.SwithException;
import com.swith.backend.global.exception.error.ErrorCode;

public class PhoneNumberConflictException extends SwithException {
    public static final SwithException EXCEPTION = new PhoneNumberConflictException();
    private PhoneNumberConflictException() { super(ErrorCode.PHONE_NUMBER_CONFLICT); }
}
