package org.flab.hyunsb.framework.persistence.adapter;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.hyunsb.application.output.MemberOutputPort;
import org.flab.hyunsb.domain.member.Member;
import org.flab.hyunsb.domain.member.MemberForCreate;
import org.flab.hyunsb.framework.persistence.entity.member.MemberEntity;
import org.flab.hyunsb.framework.persistence.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements MemberOutputPort {

    private final MemberRepository memberRepository;

    @Override
    public Member saveMember(Member member) {
        MemberEntity memberEntity = MemberEntity.from(member);
        memberEntity = memberRepository.save(memberEntity);

        return memberEntity.toDomain();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email)
            .map(MemberEntity::toDomain)
            .or(Optional::empty);
    }
}
