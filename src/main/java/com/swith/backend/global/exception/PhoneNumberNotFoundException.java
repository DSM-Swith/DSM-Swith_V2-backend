package com.swith.backend.global.exception;

import com.swith.backend.global.exception.error.SwithException;
import com.swith.backend.global.exception.error.ErrorCode;

public class PhoneNumberNotFoundException extends SwithException {
    public static final SwithException EXCEPTION = new PhoneNumberNotFoundException();
    private PhoneNumberNotFoundException() {
        super(ErrorCode.PHONE_NUMBER_NOT_FOUND);
    }
}
