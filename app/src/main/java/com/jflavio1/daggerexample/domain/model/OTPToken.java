package com.jflavio1.daggerexample.domain.model;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class OTPToken{
        public String getToken() throws InvalidKeyException, NoSuchAlgorithmException {
            final TimeBasedOneTimePasswordGenerator totp = new TimeBasedOneTimePasswordGenerator();
            final SecretKey secretKey;
            {
                final KeyGenerator keyGenerator = KeyGenerator.getInstance(totp.getAlgorithm());

                // SHA-1 and SHA-256 prefer 64-byte (512-bit) keys; SHA512 prefers 128-byte (1024-bit) keys
                keyGenerator.init(512);

                secretKey = keyGenerator.generateKey();
            }
            final Date now = new Date();
            return Integer.toString(totp.generateOneTimePassword(secretKey, now));
        }
}
