package com.jflavio1.daggerexample;

import com.jflavio1.daggerexample.domain.model.HmacOneTimePasswordGenerator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class HmacOneTimePasswordGeneratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testHmacOneTimePasswordGeneratorWithShortPasswordLength() throws NoSuchAlgorithmException {
        new HmacOneTimePasswordGenerator(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHmacOneTimePasswordGeneratorWithLongPasswordLength() throws NoSuchAlgorithmException {
        new HmacOneTimePasswordGenerator(9);
    }

    @Test(expected = NoSuchAlgorithmException.class)
    public void testHmacOneTimePasswordGeneratorWithBogusAlgorithm() throws NoSuchAlgorithmException {
        new HmacOneTimePasswordGenerator(6, "Definitely not a real algorithm");
    }

    @Test
    public void testGetPasswordLength() throws NoSuchAlgorithmException {
        final int passwordLength = 7;
        assertEquals(passwordLength, new HmacOneTimePasswordGenerator(passwordLength).getPasswordLength());
    }

    @Test
    public void testGetAlgorithm() throws NoSuchAlgorithmException {
        final String algorithm = "HmacSHA256";
        assertEquals(algorithm, new HmacOneTimePasswordGenerator(6, algorithm).getAlgorithm());
    }

    /**
     * Tests generation of one-time passwords using the test vectors from
     * <a href="https://tools.ietf.org/html/rfc4226#appendix-D">RFC&nbsp;4226, Appendix D</a>.
     */
    @Test
    @Parameters({
            "0, 755224",
            "1, 287082",
            "2, 359152",
            "3, 969429",
            "4, 338314",
            "5, 254676",
            "6, 287922",
            "7, 162583",
            "8, 399871",
            "9, 520489" })
    public void testGenerateOneTimePassword(final int counter, final int expectedOneTimePassword) throws Exception {
        final HmacOneTimePasswordGenerator hmacOneTimePasswordGenerator = this.getDefaultGenerator();

        final SecretKey key = new SecretKeySpec("12345678901234567890".getBytes(StandardCharsets.US_ASCII), "RAW");
        assertEquals(expectedOneTimePassword, hmacOneTimePasswordGenerator.generateOneTimePassword(key, counter));
    }

    protected HmacOneTimePasswordGenerator getDefaultGenerator() throws NoSuchAlgorithmException {
        return new HmacOneTimePasswordGenerator();
    }
}
