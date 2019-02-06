package com.jflavio1.daggerexample.domain.model;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
 * <p>Generates HMAC-based one-time passwords (HOTP) as specified in
 * <a href="https://tools.ietf.org/html/rfc4226">RFC&nbsp;4226</a>.</p>
 *
 * <p>{@code HmacOneTimePasswordGenerator} instances are thread-safe and may be shared and re-used across multiple
 * threads.</p>
 *
 * @author <a href="https://github.com/jchambers">Jon Chambers</a>
 */
public class HmacOneTimePasswordGenerator {
    private final String algorithm;
    private final int passwordLength;

    private final int modDivisor;


    public static final int DEFAULT_PASSWORD_LENGTH = 6;

    public static final String HOTP_HMAC_ALGORITHM = "HmacSHA1";

    public HmacOneTimePasswordGenerator() throws NoSuchAlgorithmException {
        this(DEFAULT_PASSWORD_LENGTH);
    }

    public HmacOneTimePasswordGenerator(final int passwordLength) throws NoSuchAlgorithmException {
        this(passwordLength, HOTP_HMAC_ALGORITHM);
    }

    protected HmacOneTimePasswordGenerator(final int passwordLength, final String algorithm) throws NoSuchAlgorithmException {
        switch (passwordLength) {
            case 6: {
                this.modDivisor = 1_000_000;
                break;
            }

            case 7: {
                this.modDivisor = 10_000_000;
                break;
            }

            case 8: {
                this.modDivisor = 100_000_000;
                break;
            }

            default: {
                throw new IllegalArgumentException("Password length must be between 6 and 8 digits.");
            }
        }

        this.passwordLength = passwordLength;

        // Our purpose here is just to throw an exception immediately if the algorithm is bogus.
        Mac.getInstance(algorithm);
        this.algorithm = algorithm;
    }


    public int generateOneTimePassword(final SecretKey secretKey, final long counter) throws InvalidKeyException {
        final Mac mac;

        try {
            mac = Mac.getInstance(this.algorithm);
            mac.init(secretKey);
        } catch (final NoSuchAlgorithmException e) {
            // This should never happen since we verify that the algorithm is legit in the constructor.
            throw new RuntimeException(e);
        }

        final ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(0, counter);

        final byte[] hmac = mac.doFinal(buffer.array());
        final int offset = hmac[hmac.length - 1] & 0x0f;

        for (int i = 0; i < 4; i++) {
            // Note that we're re-using the first four bytes of the buffer here; we just ignore the latter four from
            // here on out.
            buffer.put(i, hmac[i + offset]);
        }

        final int hotp = buffer.getInt(0) & 0x7fffffff;

        return hotp % this.modDivisor;
    }

    public int getPasswordLength() {
        return this.passwordLength;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }
}
