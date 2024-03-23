package org.flab.hyunsb.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.hyunsb.application.exception.authentication.MemberPasswordNotMatchedException;
import org.flab.hyunsb.application.exception.constraint.MemberEmailDuplicatedException;
import org.flab.hyunsb.application.exception.constraint.MemberNotFoundException;
import org.flab.hyunsb.application.output.MemberOutputPort;
import org.flab.hyunsb.application.usecase.member.CreateMemberUseCase;
import org.flab.hyunsb.application.usecase.member.LoginUseCase;
import org.flab.hyunsb.application.validator.RegionValidator;
import org.flab.hyunsb.domain.member.Member;
import org.flab.hyunsb.domain.member.MemberForCreate;
import org.flab.hyunsb.domain.member.MemberForLogin;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService implements CreateMemberUseCase, LoginUseCase {

    private final MemberOutputPort memberOutputPort;
    private final RegionValidator regionValidator;
    private final ActorTokenService jwtService;

    @Override
    public Member createMember(final MemberForCreate memberForCreate) {
        regionValidator.validateRegionId(memberForCreate.regionId());
        validateEmailDuplication(memberForCreate.email());

        Member member = Member.from(memberForCreate);
        return memberOutputPort.saveMember(member);
    }

    private void validateEmailDuplication(String email) {
        memberOutputPort.findByEmail(email).ifPresent(member -> {
            throw new MemberEmailDuplicatedException();
        });
    }

    @Override
    public String login(MemberForLogin memberForLogin) {
        Member member = memberOutputPort.findByEmail(memberForLogin.email())
            .orElseThrow(MemberNotFoundException::new);
        checkPassword(member, memberForLogin.password());

        return jwtService.createActorToken(member.id());
    }

    private void checkPassword(Member member, String password) {
        if (!member.isMatchingPassword(password)) {
            throw new MemberPasswordNotMatchedException();
        }
    }
}
