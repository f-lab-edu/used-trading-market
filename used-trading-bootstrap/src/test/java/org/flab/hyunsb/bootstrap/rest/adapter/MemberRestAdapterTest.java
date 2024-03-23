package org.flab.hyunsb.bootstrap.rest.adapter;

import static org.flab.hyunsb.application.exception.message.MemberErrorMessage.MEMBER_NOT_EXIST;
import static org.flab.hyunsb.application.exception.message.MemberErrorMessage.PASSWORD_NOT_MATCHED;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flab.hyunsb.application.exception.authentication.MemberPasswordNotMatchedException;
import org.flab.hyunsb.application.exception.constraint.MemberNotFoundException;
import org.flab.hyunsb.application.usecase.member.ActorTokenAuthUseCase;
import org.flab.hyunsb.application.usecase.member.CreateMemberUseCase;
import org.flab.hyunsb.application.usecase.member.LoginUseCase;
import org.flab.hyunsb.bootstrap.config.ActorTokenConfig;
import org.flab.hyunsb.bootstrap.rest.dto.member.MemberCreateRequest;
import org.flab.hyunsb.bootstrap.rest.dto.member.MemberLoginRequest;
import org.flab.hyunsb.bootstrap.restdocs.AbstractRestDocsTests;
import org.flab.hyunsb.domain.member.Member;
import org.flab.hyunsb.domain.member.MemberForCreate;
import org.flab.hyunsb.domain.member.MemberForLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(MemberRestAdapter.class)
class MemberRestAdapterTest extends AbstractRestDocsTests {

    private static final Long TEST_REGION_ID = 1L;
    private static final String TEST_EMAIL = "email@email";
    private static final String TEST_PASSWORD = "password";
    private static final String TEST_NICKNAME = "nickname";

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateMemberUseCase createMemberUseCase;

    @MockBean
    private ActorTokenAuthUseCase actorTokenAuthUseCase;

    @MockBean
    private LoginUseCase loginUseCase;

    @Test
    @DisplayName("[회원 등록 핸들러 성공 테스트] 회원을 등록한다.")
    public void registrationMember_successTest() throws Exception {
        // Given
        String requestUrl = "/api/v1/members";

        String requestBody = objectMapper.writeValueAsString(
            new MemberCreateRequest(TEST_REGION_ID, TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME)
        );

        when(createMemberUseCase.createMember(any(MemberForCreate.class)))
            .then(invocation -> {
                MemberForCreate m = invocation.getArgument(0);
                return new Member(1L, m.regionId(), m.email(), m.password(), m.nickname());
            });

        // When & Then
        mockMvc.perform(post(requestUrl)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("email", is(TEST_EMAIL)))
            .andExpect(jsonPath("nickname", is(TEST_NICKNAME)));
    }

    @Test
    @DisplayName("[회원 등록 핸들러 실패 테스트] 제약 조건을 만족하지 않는 데이터가 전달된 경우 ErrorResponse 객체를 반환한다.")
    public void registrationMember_failureTest() throws Exception {
        // Given
        String requestUrl = "/api/v1/members";

        String invalidEmail = "invalidEmail";
        String requestBody = objectMapper.writeValueAsString(
            new MemberCreateRequest(TEST_REGION_ID, invalidEmail, TEST_PASSWORD, TEST_NICKNAME)
        );

        // When & Then
        mockMvc.perform(post(requestUrl)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("[회원 로그인 핸들러 성공 테스트] 로그인을 시도하고 성공 시 토큰을 반환한다.")
    public void loginMember_successTest() throws Exception {
        // Given
        String requestUrl = "/api/v1/members/login";

        String testToken = "testToken";
        String requestBody = objectMapper.writeValueAsString(
            new MemberLoginRequest(TEST_EMAIL, TEST_PASSWORD));

        Mockito.when(loginUseCase.login(ArgumentMatchers.any(MemberForLogin.class)))
            .thenReturn(testToken);

        // When & Then
        mockMvc.perform(post(requestUrl)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(header().string(ActorTokenConfig.ACTOR_TOKEN_HEADER, testToken));
    }

    @Test
    @DisplayName("[회원 로그인 핸들러 실패 테스트] 존재하지 않는 이메일을 로그인 요청 했을 시 오류를 처리한다.")
    public void loginMember_failureTest_nonExistEmail() throws Exception {
        // Given
        String requestUrl = "/api/v1/members/login";

        String requestBody = objectMapper.writeValueAsString(
            new MemberLoginRequest(TEST_EMAIL, TEST_PASSWORD));

        Mockito.when(loginUseCase.login(ArgumentMatchers.any(MemberForLogin.class)))
            .thenThrow(new MemberNotFoundException());

        // When & Then
        mockMvc.perform(post(requestUrl)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("detail", is(MEMBER_NOT_EXIST.getMessage())));
    }

    @Test
    @DisplayName("[회원 로그인 핸들러 실패 테스트] 올바르지 않는 패스워드로 로그인 요청 했을 시 오류를 처리한다.")
    public void loginMember_failureTest_notMatchedEmail() throws Exception {
        // Given
        String requestUrl = "/api/v1/members/login";

        String requestBody = objectMapper.writeValueAsString(
            new MemberLoginRequest(TEST_EMAIL, TEST_PASSWORD));

        Mockito.when(loginUseCase.login(ArgumentMatchers.any(MemberForLogin.class)))
            .thenThrow(new MemberPasswordNotMatchedException());

        // When & Then
        mockMvc.perform(post(requestUrl)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("detail", is(PASSWORD_NOT_MATCHED.getMessage())));
    }
}