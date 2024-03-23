package org.flab.hyunsb.application.exception.constraint;

import static org.flab.hyunsb.application.exception.message.MemberErrorMessage.MEMBER_NOT_EXIST;

public class MemberNotFoundException extends ConstraintException {

    public MemberNotFoundException() {
        super(MEMBER_NOT_EXIST.getMessage());
    }
}
