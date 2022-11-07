package com.swith.backend.global.exception;

import com.swith.backend.global.exception.error.ErrorCode;
import com.swith.backend.global.exception.error.SwithException;

public class PhoneNumberNotSmsException extends SwithException {
    public static final SwithException EXCEPTION = new PhoneNumberNotSmsException();
    private PhoneNumberNotSmsException() {
        super(ErrorCode.PHONE_NUMBER_NOT_SMS);
    }
}
