package com.eval.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;


public class PasswordUtil {

    private static final SecureRandom RNG = new SecureRandom();

    
    public static String newSalt() {
        byte[] b = new byte[8];
        RNG.nextBytes(b);
        return toHex(b);
    }

    
    public static String hash(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest((password + salt).getBytes(StandardCharsets.UTF_8));
            return toHex(digest);
        } catch (Exception e) {
            throw new RuntimeException("Hash error", e);
        }
    }

    
    public static boolean verify(String password, String salt, String expectedHash) {
        return hash(password, salt).equalsIgnoreCase(expectedHash);
    }

    private static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
