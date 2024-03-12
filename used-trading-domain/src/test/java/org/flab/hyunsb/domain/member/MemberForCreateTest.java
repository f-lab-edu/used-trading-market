package org.flab.hyunsb.domain.member;

import static org.junit.jupiter.api.Assertions.*;

import org.flab.hyunsb.domain.member.mock.MockEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberForCreateTest {

    private static final String ENCRYPT_PREFIX = "encrypted ";
    private MockEncryptor mockEncryptor;

    @BeforeEach
    void setUp() {
        mockEncryptor = new MockEncryptor(ENCRYPT_PREFIX);
    }

    @Test
    @DisplayName("[비밀번호 암호화 성공 테스트] 파라매터로 넘어온 암호화기를 통해 비밀번호를 암호화 한다.")
    public void Test() {
        // Given
        Long regionId = 1L;
        String email = "email";
        String password = "password";
        String nickname = "nickname";

        MemberForCreate memberForCreate = new MemberForCreate(regionId, email, password, nickname);
        // When
        memberForCreate.encryptPassword(mockEncryptor);
        // Then
        Assertions.assertAll(
            () -> Assertions.assertNotEquals(memberForCreate.getPassword(), password)
        );
    }
}