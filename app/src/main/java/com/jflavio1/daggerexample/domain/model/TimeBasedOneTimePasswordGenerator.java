package com.jflavio1.daggerexample.domain.model;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>Generates time-based one-time passwords (TOTP) as specified in
 * <a href="https://tools.ietf.org/html/rfc6238">RFC&nbsp;6238</a>.</p>
 *
 * <p>{@code TimeBasedOneTimePasswordGenerator} instances are thread-safe and may be shared and re-used across multiple
 * threads.</p>
 *
 * @author <a href="https://github.com/jchambers">Jon Chambers</a>
 */
public class TimeBasedOneTimePasswordGenerator extends HmacOneTimePasswordGenerator {
    private final long timeStepMillis;

    /**
     * A string identifier for the HMAC-SHA1 algorithm (required by HOTP and allowed by TOTP). HMAC-SHA1 is the default
     * algorithm for TOTP.
     */
    public static final String TOTP_ALGORITHM_HMAC_SHA1 = "HmacSHA1";

    /**
     * A string identifier for the HMAC-SHA256 algorithm (allowed by TOTP).
     */
    public static final String TOTP_ALGORITHM_HMAC_SHA256 = "HmacSHA256";

    /**
     * A string identifier for the HMAC-SHA512 algorithm (allowed by TOTP).
     */
    public static final String TOTP_ALGORITHM_HMAC_SHA512 = "HmacSHA512";

    public TimeBasedOneTimePasswordGenerator() throws NoSuchAlgorithmException {
        this(60, TimeUnit.SECONDS);
    }

    public TimeBasedOneTimePasswordGenerator(final long timeStep, final TimeUnit timeStepUnit) throws NoSuchAlgorithmException {
        this(timeStep, timeStepUnit, HmacOneTimePasswordGenerator.DEFAULT_PASSWORD_LENGTH);
    }


    public TimeBasedOneTimePasswordGenerator(final long timeStep, final TimeUnit timeStepUnit, final int passwordLength) throws NoSuchAlgorithmException {
        this(timeStep, timeStepUnit, passwordLength, TOTP_ALGORITHM_HMAC_SHA1);
    }

    public TimeBasedOneTimePasswordGenerator(final long timeStep, final TimeUnit timeStepUnit, final int passwordLength, final String algorithm) throws NoSuchAlgorithmException {
        super(passwordLength, algorithm);

        this.timeStepMillis = timeStepUnit.toMillis(timeStep);
    }

    public int generateOneTimePassword(final SecretKey secretKey, final Date timestamp) throws InvalidKeyException {
        return this.generateOneTimePassword(secretKey, timestamp.getTime() / this.timeStepMillis);
    }

    public long getTimeStep(final TimeUnit timeUnit) {
        return timeUnit.convert(this.timeStepMillis, TimeUnit.MILLISECONDS);
    }
}