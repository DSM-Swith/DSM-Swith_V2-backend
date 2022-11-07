package com.swith.backend.global.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    IMAGE_NOT_SAVE(400, "사진이 정상적으로 저장되지 못했습니다."),
    IMAGE_BAD_REQUEST(400, "이미지가 올바르지 않습니다."),

    USER_NOT_FOUND(404, "아이디를 찾을 수 없습니다."),
    DIARY_NOT_FOUND(404, "핸드폰 번호를 찾을 수 없습니다."),
    PHONE_NUMBER_NOT_FOUND(404, "핸드폰 번호를 찾을 수 없습니다."),
    PHONE_NUMBER_NOT_SMS(404, "핸드폰 번호를 통해 발신된 인증번호가 없습니다."),

    TOKEN_UNAUTHORIZED(401, "토큰이 유효하지 않습니다."),

    ID_CONFLICT(409, "아이디가 중복되었습니다."),
    PHONE_NUMBER_CONFLICT(409, "이메일이 중복되었습니다."),
    PASSWORD_CONFLICT(409, "비밀번호가 일치하지 않습니다.");

    private final int status;
    private final String message;
}
