package org.flab.hyunsb.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.hyunsb.domain.Encryptor.Encryptor;

@Getter
@AllArgsConstructor
public class MemberForCreate {

    private Long regionId;
    private String email;
    private String password;
    private String nickname;

    public void encryptPassword(Encryptor encryptor) {
        password = encryptor.encrypt(password);
    }
}
