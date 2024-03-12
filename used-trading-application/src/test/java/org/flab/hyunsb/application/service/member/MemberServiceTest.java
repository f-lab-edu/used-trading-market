package org.flab.hyunsb.application.service.member;

import static org.assertj.core.api.Assertions.*;

import org.flab.hyunsb.domain.Encryptor.Encryptor;
import org.flab.hyunsb.application.exception.MemberEmailDuplicatedException;
import org.flab.hyunsb.application.exception.RegionInvalidException;
import org.flab.hyunsb.application.exception.message.MemberErrorMessage;
import org.flab.hyunsb.application.exception.message.RegionErrorMessage;
import org.flab.hyunsb.application.output.MemberOutputPort;
import org.flab.hyunsb.application.service.MemberService;
import org.flab.hyunsb.application.service.member.mock.MockMemberOutputPort;
import org.flab.hyunsb.application.service.member.mock.MockPasswordEncryptor;
import org.flab.hyunsb.application.service.member.mock.MockValidateRegionUseCase;
import org.flab.hyunsb.application.usecase.region.ValidateRegionUseCase;
import org.flab.hyunsb.domain.member.Member;
import org.flab.hyunsb.domain.member.MemberForCreate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    private static final String DUPLICATION_EMAIL = "duplication Email";
    private static final String ENCRYPT_PREFIX = "encrypted ";
    private static final Long INVALID_REGION_ID = 0L;

    private MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = generateTestMemberService();
    }

    private static MemberService generateTestMemberService() {
        Encryptor mockEncryptor = new MockPasswordEncryptor(ENCRYPT_PREFIX);
        MemberOutputPort mockMemberOutputPort = new MockMemberOutputPort(DUPLICATION_EMAIL);
        ValidateRegionUseCase validateRegionUseCase = new MockValidateRegionUseCase(INVALID_REGION_ID);

        return new MemberService(mockMemberOutputPort, mockEncryptor, validateRegionUseCase);
    }

    @Nested
    @DisplayName("회원 생성 서비스 단위 테스트")
    class MemberServiceCreate {

        @Test
        @DisplayName("[회원 생성 성공 테스트] 회원을 생성한다.")
        public void createMember_successTest() {
            // Given
            Long regionId = 1L;
            String email = "email";
            String password = "password";
            String nickname = "nickname";

            MemberForCreate memberForCreate = new MemberForCreate(regionId, email, password, nickname);

            // When
            Member actualMember = memberService.createMember(memberForCreate);

            // Then
            Assertions.assertAll(
                () -> Assertions.assertEquals(regionId, actualMember.regionId()),
                () -> Assertions.assertEquals(email, actualMember.email()),
                () -> Assertions.assertEquals(nickname, actualMember.nickname()),
                () -> Assertions.assertNotEquals(password, actualMember.password()),
                () -> Assertions.assertEquals(ENCRYPT_PREFIX + password, actualMember.password())
            );
        }

        @Test
        @DisplayName("[회원 생성 실패 테스트] 중복된 이메일을 요청한 경우 예외를 발생한다.")
        public void createMember_failureTest_causeDuplicatedEmail() {
            // Given
            Long regionId = 1L;
            String email = DUPLICATION_EMAIL;
            String password = "password";
            String nickname = "nickname";

            MemberForCreate memberForCreate = new MemberForCreate(regionId, email, password, nickname);

            // When & Then
            Assertions.assertAll(
                () -> assertThatThrownBy(() -> memberService.createMember(memberForCreate))
                    .isInstanceOf(MemberEmailDuplicatedException.class)
                    .hasMessage(MemberErrorMessage.EMAIL_DUPLICATED.getMessage())
            );
        }

        @Test
        @DisplayName("[회원 생성 실패 테스트] 유효하지 않은 지역 번호를 요청한 경우 예외를 발생한다.")
        public void createMember_failureTest_causeInvalidRegionId() {
            // Given
            Long regionId = INVALID_REGION_ID;
            String email = "email";
            String password = "password";
            String nickname = "nickname";

            MemberForCreate memberForCreate = new MemberForCreate(regionId, email, password, nickname);

            // When & Then
            Assertions.assertAll(
                () -> assertThatThrownBy(() -> memberService.createMember(memberForCreate))
                    .isInstanceOf(RegionInvalidException.class)
                    .hasMessage(RegionErrorMessage.INVALID_REGION_ID.getMessage())
            );
        }
    }
}
