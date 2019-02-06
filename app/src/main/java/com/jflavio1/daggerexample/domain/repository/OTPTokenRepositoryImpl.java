package com.jflavio1.daggerexample.domain.repository;

import com.jflavio1.daggerexample.domain.model.OTPToken;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class OTPTokenRepositoryImpl implements OTPTokenRepository{

    @Override
    public String getSixDigitsToken() throws NoSuchAlgorithmException, InvalidKeyException {
        OTPToken otpToken = new OTPToken();
        return otpToken.getToken();
    }
}
