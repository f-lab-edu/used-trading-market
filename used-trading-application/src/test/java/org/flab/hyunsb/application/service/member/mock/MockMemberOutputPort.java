package org.flab.hyunsb.application.service.member.mock;

import java.util.Optional;
import org.flab.hyunsb.application.output.MemberOutputPort;
import org.flab.hyunsb.domain.member.Member;

public class MockMemberOutputPort implements MemberOutputPort {

    private static final Member MOCK_MEMBER = new Member(
        1L, 1L, "email", "password", "nickname"
    );

    private final String duplication_email;

    public MockMemberOutputPort(String duplicationEmail) {
        duplication_email = duplicationEmail;
    }

    @Override
    public Member saveMember(Member member) {
        return member;
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        if (duplication_email.equals(email)) {
            return Optional.of(MOCK_MEMBER);
        }
        return Optional.empty();
    }
}
