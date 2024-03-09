package org.flab.hyunsb.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberForCreate {

    private Long regionId;
    private String email;
    private String password;
    private String nickname;

    public void setEncryptedPassword(String encryptedPassword) {
        password = encryptedPassword;
    }
}
