package org.flab.hyunsb.domain.member.encryptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordEncryptor implements Encryptor {

    private static final String ENCRYPT_ALGORITHM = "SHA-256";
    private static PasswordEncryptor passwordEncryptor;

    private final MessageDigest messageDigest;

    public static PasswordEncryptor getInstance() {
        if (Objects.isNull(passwordEncryptor)) {
            setPasswordEncryptor();
        }
        return passwordEncryptor;
    }

    private static void setPasswordEncryptor() {
        try {
            MessageDigest passwordMessageDigest = MessageDigest.getInstance(ENCRYPT_ALGORITHM);
            passwordEncryptor = new PasswordEncryptor(passwordMessageDigest);
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public String encrypt(String data) {
        byte[] encryptedData = messageDigest.digest(data.getBytes());
        return toHexString(encryptedData);
    }

    private String toHexString(byte[] byteArray) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : byteArray) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
