package org.flab.hyunsb.domain.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    @DisplayName("[회원 생성 성공 테스트] 파라매터로 넘어온 MemberForCreate 객체의 비밀번호를 암호화한 뒤 Member 객체로 파싱한다.")
    public void memberForCreateParsing_successTest() {
        // Given
        MemberForCreate memberForCreate = generateTestMemberForCreate();
        // When
        Member member = Member.from(memberForCreate);
        // Then
        Assertions.assertAll(
            () -> Assertions.assertNotEquals(member.password(), memberForCreate.password())
        );
    }

    private MemberForCreate generateTestMemberForCreate() {
        Long regionId = 1L;
        String email = "email";
        String password = "password";
        String nickname = "nickname";

        return new MemberForCreate(regionId, email, password, nickname);
    }

    @Test
    @DisplayName("[회원 비밀번호 비교 성공 테스트] 파라매터로 넘어온 패스워드가 회원 정보와 일치한다면 true를 반환한다.")
    public void memberPasswordMatch_successTest() {
        // Given
        MemberForCreate memberForCreate = generateTestMemberForCreate();
        Member member = Member.from(memberForCreate);
        //When
        boolean actual = member.isMatchingPassword(memberForCreate.password());
        //Then
        Assertions.assertAll(
            () -> Assertions.assertTrue(actual)
        );
    }

    @Test
    @DisplayName("[회원 비밀번호 비교 실패 테스트] 파라매터로 넘어온 패스워드가 회원 정보와 일치하지 않는다면 false를 반환한다.")
    public void memberPasswordMatch_failureTest() {
        // Given
        String MismatchPassword = "mismatchPassword";
        MemberForCreate memberForCreate = generateTestMemberForCreate();
        Member member = Member.from(memberForCreate);
        //When
        boolean actual = member.isMatchingPassword(MismatchPassword);
        //Then
        Assertions.assertAll(
            () -> Assertions.assertFalse(actual)
        );
    }
}