package org.flab.hyunsb.domain.member;

public record Member(Long id, Long regionId, String email, String password, String nickname) {

}
