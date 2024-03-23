package org.flab.hyunsb.domain.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberForLoginTest {

    @Test
    @DisplayName("[회원 로그인 정보 생성 성공 테스트] 로그인을 위해 필요한 도메인 객체를 생성한다.")
    public void memberPasswordMatch_failureTest() {
        // Given
        String email = "email@email";
        String password = "password";
        //When
        MemberForLogin memberForLogin = new MemberForLogin(email, password);
        //Then
        Assertions.assertAll(
            () -> Assertions.assertEquals(email, memberForLogin.email()),
            () -> Assertions.assertEquals(password, memberForLogin.password())
        );
    }
}