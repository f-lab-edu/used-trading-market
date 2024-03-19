package org.flab.hyunsb.bootstrap.rest.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.flab.hyunsb.domain.member.MemberForCreate;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateRequest {

    @NotNull(message = "{validation.constraints.member.regionId.NotNull.message}")
    private Long regionId;

    @NotBlank(message = "{validation.constraints.member.email.NotBlank.message}")
    @Email(message = "{validation.constraints.member.email.Email.message}")
    private String email;

    @NotBlank(message = "{validation.constraints.member.password.NotBlank.message}")
    @Size(min = 8, max = 16, message = "{validation.constraints.member.password.Size.message}")
    private String password;

    @NotBlank(message = "{validation.constraints.member.nickname.NotBlank.message}")
    @Size(min = 2, max = 16, message = "{validation.constraints.member.nickname.Size.message}")
    private String nickname;

    public MemberForCreate toMemberForCreate() {
        return new MemberForCreate(regionId, email, password, nickname);
    }
}
