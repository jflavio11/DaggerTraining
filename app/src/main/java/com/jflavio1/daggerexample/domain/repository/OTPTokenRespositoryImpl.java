package com.jflavio1.daggerexample.domain.repository;

import com.jflavio1.daggerexample.domain.model.OTPToken;

public class OTPTokenRespositoryImpl implements OTPTokenRepository{

    static final Integer LENGTH = 6;

    @Override
    public String getSixDigitsToken() {
        OTPToken otpToken = new OTPToken();
        return otpToken.getToken(LENGTH);
    }
}
