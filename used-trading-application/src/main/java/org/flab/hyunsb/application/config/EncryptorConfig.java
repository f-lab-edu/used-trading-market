package org.flab.hyunsb.application.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.flab.hyunsb.application.encryptor"})
public class EncryptorConfig {

    private static final String ENCRYPT_ALGORITHM = "SHA-256";

    @Bean
    public MessageDigest passwordMessageDigest() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(ENCRYPT_ALGORITHM);
    }
}
