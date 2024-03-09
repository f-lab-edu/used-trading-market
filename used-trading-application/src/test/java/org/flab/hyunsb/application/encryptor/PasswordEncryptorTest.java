package org.flab.hyunsb.application.encryptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordEncryptorTest {

    private PasswordEncryptor passwordEncryptor;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        passwordEncryptor = new PasswordEncryptor(messageDigest);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test1", "password", "qwer1234!@#$"})
    @DisplayName("[암호화 비밀번호 생성 성공 테스트] 파라매터로 넘어온 데이터를 암호화한다.")
    public void createEncryptPasswordTest(String data) {
        // Given & When
        String actual = passwordEncryptor.encrypt(data);
        String expected = passwordEncryptor.encrypt(data);
        // Then
        Assertions.assertAll(
            () -> Assertions.assertEquals(actual, expected)
        );
    }
}
