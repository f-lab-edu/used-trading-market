package org.flab.hyunsb.domain.member;

import org.flab.hyunsb.domain.member.encryptor.Encryptor;
import org.flab.hyunsb.domain.member.encryptor.PasswordEncryptor;

public record Member(Long id, Long regionId, String email, String password, String nickname) {

    private static final Encryptor ENCRYPTOR = PasswordEncryptor.getInstance();

    public static Member from(MemberForCreate memberForCreate) {
        return new Member(
            null,
            memberForCreate.regionId(),
            memberForCreate.email(),
            encryptPassword(memberForCreate.password()),
            memberForCreate.nickname()
        );
    }

    private static String encryptPassword(String password) {
        return ENCRYPTOR.encrypt(password);
    }

    public boolean isMatchingPassword(String password) {
        String encryptedPassword = encryptPassword(password);
        return this.password.equals(encryptedPassword);
    }
}
