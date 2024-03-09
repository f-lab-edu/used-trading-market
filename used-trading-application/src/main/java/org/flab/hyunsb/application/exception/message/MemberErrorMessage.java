package org.flab.hyunsb.application.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorMessage {

    EMAIL_DUPLICATED("중복된 이메일 입니다.");

    private final String message;
}
