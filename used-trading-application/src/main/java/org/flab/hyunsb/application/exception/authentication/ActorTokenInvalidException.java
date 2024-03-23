package org.flab.hyunsb.application.exception.authentication;

import org.flab.hyunsb.application.exception.message.ActorTokenErrorMessage;

public class ActorTokenInvalidException extends AuthenticationException {

    public ActorTokenInvalidException(ActorTokenErrorMessage message) {
        super(message.getMessage());
    }
}
