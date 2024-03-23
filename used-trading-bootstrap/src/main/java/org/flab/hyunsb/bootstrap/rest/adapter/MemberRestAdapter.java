package org.flab.hyunsb.bootstrap.rest.adapter;

import static org.flab.hyunsb.bootstrap.config.ActorTokenConfig.ACTOR_TOKEN_HEADER;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flab.hyunsb.application.usecase.member.CreateMemberUseCase;
import org.flab.hyunsb.application.usecase.member.LoginUseCase;
import org.flab.hyunsb.bootstrap.rest.dto.member.MemberCreateRequest;
import org.flab.hyunsb.bootstrap.rest.dto.member.MemberCreateResponse;
import org.flab.hyunsb.bootstrap.rest.dto.member.MemberLoginRequest;
import org.flab.hyunsb.domain.member.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@RestController
public class MemberRestAdapter {

    private final CreateMemberUseCase createMemberUseCase;
    private final LoginUseCase loginUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MemberCreateResponse createMember(
        @Valid @RequestBody MemberCreateRequest memberCreateRequest) {
        log.info("memberCreateRequest={}", memberCreateRequest);

        Member member = createMemberUseCase.createMember(memberCreateRequest.toMemberForCreate());
        return MemberCreateResponse.from(member);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody MemberLoginRequest memberLoginRequest) {
        log.info("memberLoginRequest={}", memberLoginRequest);
        String accessToken = loginUseCase.login(memberLoginRequest.toMemberForLogin());

        return ResponseEntity.ok()
            .header(ACTOR_TOKEN_HEADER, accessToken).build();
    }
}

