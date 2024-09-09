package dev.dubrovsky.util.encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class SimplePasswordEncoder {

    private final static String ENCODE_ALGORITHM = "SHA-256";

    private SimplePasswordEncoder() {
    }

    public static String encode(String rawPassword) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
            byte[] hashedPassword = messageDigest.digest(rawPassword.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encoding password", e);
        }
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

}
