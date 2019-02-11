package com.jflavio1.daggerexample.domain.repository;

import com.jflavio1.daggerexample.domain.model.OTPToken;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class OTPTokenRepositoryImpl implements OTPTokenRepository{

    @Override
    public String getSixDigitsToken() throws NoSuchAlgorithmException, InvalidKeyException {
        OTPToken otpToken = new OTPToken();
        String token;
        do {
            token = otpToken.getToken();
        }while (token.length() != 6);
        return token;
    }


}
