package org.flab.hyunsb.application.exception;

import org.flab.hyunsb.application.exception.message.RegionErrorMessage;

public class RegionInvalidException extends ConstraintException {

    public RegionInvalidException() {
        super(RegionErrorMessage.INVALID_REGION_ID.getMessage());
    }
}
