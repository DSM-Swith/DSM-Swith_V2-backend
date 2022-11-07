package com.swith.backend.global.exception;

import com.swith.backend.global.exception.error.ErrorCode;
import com.swith.backend.global.exception.error.SwithException;

public class ImageNotSaveException extends SwithException {
	public static final SwithException EXCEPTION = new ImageNotSaveException();
	private ImageNotSaveException() {
		super(ErrorCode.IMAGE_NOT_SAVE);
	}
}