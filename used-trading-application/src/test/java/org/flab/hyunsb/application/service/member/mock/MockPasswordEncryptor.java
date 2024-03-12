package org.flab.hyunsb.application.service.member.mock;

import org.flab.hyunsb.domain.Encryptor.Encryptor;

public class MockPasswordEncryptor implements Encryptor {

    private final String encryptPrefix;

    public MockPasswordEncryptor(String encryptPrefix) {
        this.encryptPrefix = encryptPrefix;
    }

    @Override
    public String encrypt(String data) {
        return encryptPrefix + data;
    }
}
