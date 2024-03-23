package org.flab.hyunsb.application.usecase.member;

import org.flab.hyunsb.domain.member.MemberForLogin;

public interface LoginUseCase {

    String login(MemberForLogin memberForLogin);
}
