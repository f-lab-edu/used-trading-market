package org.flab.hyunsb.application.exception;

import org.flab.hyunsb.application.exception.message.MemberErrorMessage;

public class MemberEmailDuplicatedException extends ConstraintException {

    public MemberEmailDuplicatedException() {
        super(MemberErrorMessage.EMAIL_DUPLICATED.getMessage());
    }
}
