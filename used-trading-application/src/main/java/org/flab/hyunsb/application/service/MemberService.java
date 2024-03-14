package org.flab.hyunsb.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.hyunsb.application.exception.MemberEmailDuplicatedException;
import org.flab.hyunsb.application.output.MemberOutputPort;
import org.flab.hyunsb.application.usecase.member.CreateMemberUseCase;
import org.flab.hyunsb.application.usecase.region.ValidateRegionUseCase;
import org.flab.hyunsb.domain.member.Member;
import org.flab.hyunsb.domain.member.MemberForCreate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService implements CreateMemberUseCase {

    private final MemberOutputPort memberOutputPort;
    private final ValidateRegionUseCase validateRegionUseCase;

    @Override
    public Member createMember(final MemberForCreate memberForCreate) {
        validateRegionUseCase.validateRegionId(memberForCreate.regionId());
        validateEmailDuplication(memberForCreate.email());

        Member member = Member.from(memberForCreate);
        return memberOutputPort.saveMember(member);
    }

    private void validateEmailDuplication(String email) {
        memberOutputPort.findByEmail(email).ifPresent(member -> {
            throw new MemberEmailDuplicatedException();
        });
    }
}
