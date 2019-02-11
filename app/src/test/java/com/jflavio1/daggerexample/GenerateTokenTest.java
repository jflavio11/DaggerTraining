package com.jflavio1.daggerexample;

import com.jflavio1.daggerexample.domain.repository.OTPTokenRepository;
import com.jflavio1.daggerexample.domain.repository.OTPTokenRepositoryImpl;

import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;


public class GenerateTokenTest {

    private OTPTokenRepository repository = new OTPTokenRepositoryImpl();

    @Test
    public void verifyTokenLength() throws InvalidKeyException, NoSuchAlgorithmException {
        assertEquals(6, repository.getSixDigitsToken().length());
    }
}
