package org.flab.hyunsb.application.usecase.member;

import org.flab.hyunsb.domain.member.Member;
import org.flab.hyunsb.domain.member.MemberForCreate;

public interface CreateMemberUseCase {

    Member createMember(MemberForCreate memberForCreate);
}
