package org.flab.hyunsb.application.output;

import java.util.Optional;
import org.flab.hyunsb.domain.member.Member;
import org.flab.hyunsb.domain.member.MemberForCreate;

public interface MemberOutputPort {

    Member saveMember(Member member);

    Optional<Member> findByEmail(String email);
}
