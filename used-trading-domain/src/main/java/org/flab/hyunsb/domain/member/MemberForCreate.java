package org.flab.hyunsb.domain.member;

public record MemberForCreate(Long regionId, String email, String password, String nickname) {

}
