package org.flab.hyunsb.bootstrap.rest.dto.member;

import org.flab.hyunsb.domain.member.Member;

public record MemberCreateResponse(String email, String nickname) {

    public static MemberCreateResponse from(Member member) {
        return new MemberCreateResponse(member.email(), member.nickname());
    }
}
