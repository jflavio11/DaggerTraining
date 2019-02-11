package com.jflavio1.daggerexample;

import com.jflavio1.daggerexample.domain.repository.OTPTokenRepository;
import com.jflavio1.daggerexample.domain.repository.OTPTokenRepositoryImpl;

import org.junit.Assert;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class TokenGenerationTest {

    private OTPTokenRepository repository = new OTPTokenRepositoryImpl();

    @Test
    public void verifyTokenLength() throws InvalidKeyException, NoSuchAlgorithmException {
        Assert.assertEquals(6, repository.getSixDigitsToken().length());
    }
}
