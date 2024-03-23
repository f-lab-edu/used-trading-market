package org.flab.hyunsb.application.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorMessage {

    EMAIL_DUPLICATED("중복된 이메일 입니다."),
    MEMBER_NOT_EXIST("회원 정보가 존재하지 않습니다."),
    PASSWORD_NOT_MATCHED("비밀번호가 일치하지 않습니다.");

    private final String message;
}
