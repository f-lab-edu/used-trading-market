package org.flab.hyunsb.application.exception.authentication;

import static org.flab.hyunsb.application.exception.message.MemberErrorMessage.PASSWORD_NOT_MATCHED;

public class MemberPasswordNotMatchedException extends AuthenticationException {

    public MemberPasswordNotMatchedException() {
        super(PASSWORD_NOT_MATCHED.getMessage());
    }
}
