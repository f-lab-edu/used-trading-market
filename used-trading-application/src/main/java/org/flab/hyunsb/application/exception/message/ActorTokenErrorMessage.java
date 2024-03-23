package org.flab.hyunsb.application.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActorTokenErrorMessage {

    TOKEN_FORMAT_INVALID("토큰 형식이 올바르지 않습니다."),
    TOKEN_EXPIRED("토큰이 만료되었습니다."),
    TOKEN_AUTHENTICATION_FAILED("토큰 인증에 실패했습니다.");

    private final String message;
}
