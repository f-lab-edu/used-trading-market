package org.flab.hyunsb.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.hyunsb.application.encryptor.Encryptor;
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
    private final Encryptor passwordEncryptor;
    private final ValidateRegionUseCase validateRegionUseCase;

    @Override
    public Member createMember(MemberForCreate memberForCreate) {
        validateRegionUseCase.validateRegionId(memberForCreate.getRegionId());
        validateEmailDuplication(memberForCreate.getEmail());

        setEncryptedPassword(memberForCreate);

        return memberOutputPort.saveMember(memberForCreate);
    }

    private void validateEmailDuplication(String email) {
        memberOutputPort.findByEmail(email).ifPresent(member -> {
            throw new MemberEmailDuplicatedException();
        });
    }

    private void setEncryptedPassword(MemberForCreate memberForCreate) {
        String encryptedPassword = passwordEncryptor.encrypt(memberForCreate.getPassword());
        memberForCreate.setEncryptedPassword(encryptedPassword);
    }
}
