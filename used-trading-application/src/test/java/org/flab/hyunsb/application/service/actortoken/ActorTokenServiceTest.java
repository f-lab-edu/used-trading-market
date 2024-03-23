package org.flab.hyunsb.application.service.actortoken;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.flab.hyunsb.application.exception.message.ActorTokenErrorMessage.TOKEN_AUTHENTICATION_FAILED;
import static org.flab.hyunsb.application.exception.message.ActorTokenErrorMessage.TOKEN_EXPIRED;
import static org.flab.hyunsb.application.exception.message.ActorTokenErrorMessage.TOKEN_FORMAT_INVALID;

import java.util.Date;
import org.flab.hyunsb.application.exception.authentication.ActorTokenInvalidException;
import org.flab.hyunsb.application.service.ActorTokenService;
import org.flab.hyunsb.application.service.actortoken.mock.MockDateGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ActorTokenServiceTest {

    private MockDateGenerator mockDateGenerator;
    private ActorTokenService actorTokenService;

    @BeforeEach
    void setUp() {
        mockDateGenerator = new MockDateGenerator();
        String testKey = "C67062E95AE731397CF69BE6E46E01941891133D58FAAB2A26FC249CD4636BCF";

        actorTokenService = new ActorTokenService(mockDateGenerator, testKey);
    }

    @Test
    @DisplayName("[로그인 토큰 생성 성공 테스트] 회원 id를 파라매터로 받아 해당 정보를 포함하는 JWT를 생성한다.")
    public void createActorToken_successTest() {
        // Given
        Long actorId = 1L;
        // When
        String actorToken = actorTokenService.createActorToken(actorId);
        Long actual = actorTokenService.authenticate(actorToken);
        // Then
        Assertions.assertAll(
            () -> Assertions.assertEquals(actorId, actual)
        );
    }

    @Test
    @DisplayName("[로그인 토큰 인증 실패 테스트] 유효하지 않은 토큰 인증을 시도할 시 예외를 발생한다.")
    public void authenticateActorToken_failureTest_invalidToken() {
        // Given
        Long actorId = 1L;
        String actorToken = actorTokenService.createActorToken(actorId) + "invalid";
        // When & Then
        Assertions.assertAll(
            () -> assertThatThrownBy(() -> actorTokenService.authenticate(actorToken))
                .isInstanceOf(ActorTokenInvalidException.class)
                .hasMessage(TOKEN_AUTHENTICATION_FAILED.getMessage())
        );
    }

    @Test
    @DisplayName("[로그인 토큰 인증 실패 테스트] 유효하지 않은 포맷의 토큰 인증을 시도할 시 예외를 발생한다.")
    public void authenticateActorToken_failureTest_invalidTokenFormat() {
        // Given
        Long actorId = 1L;
        String actorToken = "invalid" + actorTokenService.createActorToken(actorId);
        // When & Then
        Assertions.assertAll(
            () -> assertThatThrownBy(() -> actorTokenService.authenticate(actorToken))
                .isInstanceOf(ActorTokenInvalidException.class)
                .hasMessage(TOKEN_FORMAT_INVALID.getMessage())
        );
    }

    @Test
    @DisplayName("[로그인 토큰 인증 실패 테스트] 유효 기간이 지난 토큰 인증을 시도할 시 예외를 발생한다.")
    public void authenticateActorToken_failureTest_expiredToken() {
        // Given
        Long actorId = 1L;
        String actorToken = actorTokenService.createActorToken(actorId);
        mockDateGenerator.setCurrentDate(new Date(System.currentTimeMillis() + 259200000));
        // When & Then
        Assertions.assertAll(
            () -> assertThatThrownBy(() -> actorTokenService.authenticate(actorToken))
                .isInstanceOf(ActorTokenInvalidException.class)
                .hasMessage(TOKEN_EXPIRED.getMessage())
        );
    }
}