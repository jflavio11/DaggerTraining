package com.jflavio1.daggerexample.domain.repository;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface OTPTokenRepository {
    String getSixDigitsToken() throws NoSuchAlgorithmException, InvalidKeyException;
}
