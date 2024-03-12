package org.flab.hyunsb.domain.member.mock;

import org.flab.hyunsb.domain.Encryptor.Encryptor;

public class MockEncryptor implements Encryptor {

    private final String encryptPrefix;

    public MockEncryptor(String encryptPrefix) {
        this.encryptPrefix = encryptPrefix;
    }

    @Override
    public String encrypt(String data) {
        return encryptPrefix + data;
    }
}
