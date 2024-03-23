package org.flab.hyunsb.bootstrap.rest.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.flab.hyunsb.domain.member.MemberForLogin;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginRequest {

    @NotBlank(message = "{validation.constraints.member.email.NotBlank.message}")
    @Email(message = "{validation.constraints.member.email.Email.message}")
    private String email;

    @NotBlank(message = "{validation.constraints.member.password.NotBlank.message}")
    @Size(min = 8, max = 16, message = "{validation.constraints.member.password.Size.message}")
    private String password;

    public MemberForLogin toMemberForLogin() {
        return new MemberForLogin(email, password);
    }
}
