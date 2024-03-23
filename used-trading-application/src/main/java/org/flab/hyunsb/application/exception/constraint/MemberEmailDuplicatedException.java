package org.flab.hyunsb.application.exception.constraint;

import static org.flab.hyunsb.application.exception.message.MemberErrorMessage.EMAIL_DUPLICATED;

public class MemberEmailDuplicatedException extends ConstraintException {

    public MemberEmailDuplicatedException() {
        super(EMAIL_DUPLICATED.getMessage());
    }
}
