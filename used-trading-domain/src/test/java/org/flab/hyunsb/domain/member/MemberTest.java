package org.flab.hyunsb.domain.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    @DisplayName("[회원 생성 성공 테스트] 파라매터로 넘어온 MemberForCreate 객체의 비밀번호를 암호화한 뒤 Member 객체로 파싱한다.")
    public void memberForCreateParsing_successTest() {
        // Given
        Long regionId = 1L;
        String email = "email";
        String password = "password";
        String nickname = "nickname";

        MemberForCreate memberForCreate = new MemberForCreate(regionId, email, password, nickname);
        // When
        Member member = Member.from(memberForCreate);
        // Then
        Assertions.assertAll(
            () -> Assertions.assertNotEquals(member.password(), password)
        );
    }
}