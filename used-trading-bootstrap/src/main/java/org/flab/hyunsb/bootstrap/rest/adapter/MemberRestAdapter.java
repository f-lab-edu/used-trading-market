package org.flab.hyunsb.bootstrap.rest.adapter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flab.hyunsb.application.usecase.member.CreateMemberUseCase;
import org.flab.hyunsb.bootstrap.rest.dto.member.MemberCreateRequest;
import org.flab.hyunsb.bootstrap.rest.dto.member.MemberCreateResponse;
import org.flab.hyunsb.domain.member.Member;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MemberCreateResponse createMember(
        @Valid @RequestBody MemberCreateRequest memberCreateRequest) {
        log.info("memberCreateRequest={}", memberCreateRequest);

        Member member = createMemberUseCase.createMember(memberCreateRequest.toMemberForCreate());
        return MemberCreateResponse.from(member);
    }
}

