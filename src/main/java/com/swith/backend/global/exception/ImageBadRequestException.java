package com.swith.backend.global.exception;

import com.swith.backend.global.exception.error.ErrorCode;
import com.swith.backend.global.exception.error.SwithException;

public class ImageBadRequestException extends SwithException {
	public static final SwithException EXCEPTION = new ImageBadRequestException();
	private ImageBadRequestException() {
		super(ErrorCode.IMAGE_BAD_REQUEST);
	}
}