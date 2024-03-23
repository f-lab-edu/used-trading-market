package org.flab.hyunsb.application.service.member;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.flab.hyunsb.application.exception.message.MemberErrorMessage.EMAIL_DUPLICATED;
import static org.flab.hyunsb.application.exception.message.MemberErrorMessage.MEMBER_NOT_EXIST;
import static org.flab.hyunsb.application.exception.message.MemberErrorMessage.PASSWORD_NOT_MATCHED;

import org.flab.hyunsb.application.exception.authentication.MemberPasswordNotMatchedException;
import org.flab.hyunsb.application.exception.constraint.MemberEmailDuplicatedException;
import org.flab.hyunsb.application.exception.constraint.MemberNotFoundException;
import org.flab.hyunsb.application.exception.constraint.RegionInvalidException;
import org.flab.hyunsb.application.exception.message.RegionErrorMessage;
import org.flab.hyunsb.application.output.MemberOutputPort;
import org.flab.hyunsb.application.service.ActorTokenService;
import org.flab.hyunsb.application.service.MemberService;
import org.flab.hyunsb.application.service.member.mock.MockMemberOutputPort;
import org.flab.hyunsb.application.service.member.mock.MockRegionValidator;
import org.flab.hyunsb.application.util.ActorTokenDateGenerator;
import org.flab.hyunsb.application.util.DateGenerator;
import org.flab.hyunsb.application.validator.RegionValidator;
import org.flab.hyunsb.domain.member.Member;
import org.flab.hyunsb.domain.member.MemberForCreate;
import org.flab.hyunsb.domain.member.MemberForLogin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    private static final String DUPLICATION_EMAIL = "duplication Email";
    private static final Long INVALID_REGION_ID = 0L;

    public static final Long TEST_REGION_ID = 1L;
    public static final String TEST_EMAIL = "email";
    public static final String TEST_PASSWORD = "password";
    public static final String TEST_NICKNAME = "nickname";

    private MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = generateTestMemberService();
    }

    private static MemberService generateTestMemberService() {
        Member mockMember = Member.from(
            new MemberForCreate(TEST_REGION_ID, TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME));

        MemberOutputPort mockMemberOutputPort = new MockMemberOutputPort(mockMember, DUPLICATION_EMAIL);
        RegionValidator regionValidator = new MockRegionValidator(INVALID_REGION_ID);

        DateGenerator dateGenerator = new ActorTokenDateGenerator();
        String testKey = "C67062E95AE731397CF69BE6E46E01941891133D58FAAB2A26FC249CD4636BCF";
        ActorTokenService actorTokenService = new ActorTokenService(dateGenerator, testKey);

        return new MemberService(mockMemberOutputPort, regionValidator, actorTokenService);
    }

    @Nested
    @DisplayName("회원 생성 서비스 단위 테스트")
    class MemberServiceCreate {

        @Test
        @DisplayName("[회원 생성 성공 테스트] 회원을 생성한다.")
        public void createMember_successTest() {
            // Given
            String nonDuplicationEmail = "nonDuplicationEmail";
            MemberForCreate memberForCreate =
                new MemberForCreate(TEST_REGION_ID, nonDuplicationEmail, TEST_PASSWORD, TEST_NICKNAME);

            // When
            Member actualMember = memberService.createMember(memberForCreate);

            // Then
            Assertions.assertAll(
                () -> Assertions.assertEquals(TEST_REGION_ID, actualMember.regionId()),
                () -> Assertions.assertEquals(TEST_NICKNAME, actualMember.nickname()),
                () -> Assertions.assertNotEquals(TEST_PASSWORD, actualMember.password())
            );
        }

        @Test
        @DisplayName("[회원 생성 실패 테스트] 중복된 이메일을 요청한 경우 예외를 발생한다.")
        public void createMember_failureTest_causeDuplicatedEmail() {
            // Given
            MemberForCreate memberForCreate =
                new MemberForCreate(TEST_REGION_ID, DUPLICATION_EMAIL, TEST_PASSWORD, TEST_NICKNAME);

            // When & Then
            Assertions.assertAll(
                () -> assertThatThrownBy(() -> memberService.createMember(memberForCreate))
                    .isInstanceOf(MemberEmailDuplicatedException.class)
                    .hasMessage(EMAIL_DUPLICATED.getMessage())
            );
        }

        @Test
        @DisplayName("[회원 생성 실패 테스트] 유효하지 않은 지역 번호를 요청한 경우 예외를 발생한다.")
        public void createMember_failureTest_causeInvalidRegionId() {
            // Given
            MemberForCreate memberForCreate =
                new MemberForCreate(INVALID_REGION_ID, TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME);

            // When & Then
            Assertions.assertAll(
                () -> assertThatThrownBy(() -> memberService.createMember(memberForCreate))
                    .isInstanceOf(RegionInvalidException.class)
                    .hasMessage(RegionErrorMessage.INVALID_REGION_ID.getMessage())
            );
        }
    }

    @Nested
    @DisplayName("회원 로그인 서비스 단위 테스트")
    class MemberServiceLogin {

        @Test
        @DisplayName("[회원 로그인 성공 테스트] 로그인을 성공하고 토큰을 반환한다.")
        public void loginMember_successTest() {
            // Given
            MemberForLogin memberForLogin = new MemberForLogin(TEST_EMAIL, TEST_PASSWORD);
            // When & Then
            Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> memberService.login(memberForLogin))
            );
        }

        @Test
        @DisplayName("[회원 로그인 실패 테스트] 존재하지 않는 이메일을 요청한 경우 예외를 발생한다.")
        public void loginMember_failureTest_nonExistEmail() {
            // Given
            String nonExistEmail = "nonExistEmail";
            MemberForLogin memberForLogin = new MemberForLogin(nonExistEmail, TEST_PASSWORD);
            // When & Then
            Assertions.assertAll(
                () -> assertThatThrownBy(() -> memberService.login(memberForLogin))
                    .isInstanceOf(MemberNotFoundException.class)
                    .hasMessage(MEMBER_NOT_EXIST.getMessage())
            );
        }

        @Test
        @DisplayName("[회원 로그인 실패 테스트] 일치하지 않는 비밀번호를 요청한 경우 예외를 발생한다.")
        public void loginMember_failureTest_notMatchedPassword() {
            // Given
            String notMatchedPassword = "notMatchedPassword";
            MemberForLogin memberForLogin = new MemberForLogin(TEST_EMAIL, notMatchedPassword);
            // When & Then
            Assertions.assertAll(
                () -> assertThatThrownBy(() -> memberService.login(memberForLogin))
                    .isInstanceOf(MemberPasswordNotMatchedException.class)
                    .hasMessage(PASSWORD_NOT_MATCHED.getMessage())
            );
        }
    }
}
