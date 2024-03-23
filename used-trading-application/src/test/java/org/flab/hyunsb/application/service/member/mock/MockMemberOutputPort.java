package org.flab.hyunsb.application.service.member.mock;

import java.util.Optional;
import org.flab.hyunsb.application.output.MemberOutputPort;
import org.flab.hyunsb.domain.member.Member;

public class MockMemberOutputPort implements MemberOutputPort {

    private final Member mockMember;
    private final String duplication_email;

    public MockMemberOutputPort(Member mockMember, String duplicationEmail) {
        this.mockMember = mockMember;
        duplication_email = duplicationEmail;
    }

    @Override
    public Member saveMember(Member member) {
        return member;
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        if (duplication_email.equals(email) || mockMember.email().equals(email)) {
            return Optional.of(mockMember);
        }
        return Optional.empty();
    }
}
