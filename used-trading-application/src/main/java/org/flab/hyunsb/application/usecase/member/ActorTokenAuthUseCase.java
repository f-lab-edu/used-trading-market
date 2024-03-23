package org.flab.hyunsb.application.usecase.member;

public interface ActorTokenAuthUseCase {

    Long authenticate(String actorToken);
}
