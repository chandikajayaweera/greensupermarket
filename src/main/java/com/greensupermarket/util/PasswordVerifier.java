package com.greensupermarket.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordVerifier {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}