package org.flab.hyunsb.bootstrap.rest.adapter;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flab.hyunsb.application.usecase.member.CreateMemberUseCase;
import org.flab.hyunsb.bootstrap.rest.dto.member.MemberCreateRequest;
import org.flab.hyunsb.bootstrap.restdocs.AbstractRestDocsTests;
import org.flab.hyunsb.domain.member.Member;
import org.flab.hyunsb.domain.member.MemberForCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(MemberRestAdapter.class)
class MemberRestAdapterTest extends AbstractRestDocsTests {

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CreateMemberUseCase createMemberUseCase;

    @Test
    @DisplayName("[회원 등록 핸들러 성공 테스트] 회원을 등록한다.")
    public void registrationMember_successTest() throws Exception {
        // Given
        String requestUrl = "/api/v1/members";

        Long regionId = 1L;
        String email = "email@email";
        String password = "password";
        String nickname = "nickname";
        String requestBody = objectMapper.writeValueAsString(
            new MemberCreateRequest(regionId, email, password, nickname)
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
            .andExpect(jsonPath("email", is(email)))
            .andExpect(jsonPath("nickname", is(nickname)));
    }

    @Test
    @DisplayName("[회원 등록 핸들러 실패 테스트] 제약 조건을 만족하지 않는 데이터가 전달된 경우 ErrorResponse 객체를 반환한다.")
    public void test() throws Exception {
        // Given
        String requestUrl = "/api/v1/members";

        Long regionId = 1L;
        String invalidEmail = "invalidEmail";
        String password = "password";
        String nickname = "nickname";
        String requestBody = objectMapper.writeValueAsString(
            new MemberCreateRequest(regionId, invalidEmail, password, nickname)
        );

        // When & Then
        mockMvc.perform(post(requestUrl)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}