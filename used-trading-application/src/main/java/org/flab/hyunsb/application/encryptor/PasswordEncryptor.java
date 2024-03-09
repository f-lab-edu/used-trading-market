package org.flab.hyunsb.application.encryptor;

import java.security.MessageDigest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncryptor implements Encryptor {

    private final MessageDigest messageDigest;

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
