package com.example.captchaserver;

import java.util.Random;

public class TextHandler {
    private static Random rand     = new Random();
    private static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateText(int len) {
        String generatedString = "";
        for (int i = 0; i < len; i++) {
            generatedString += rand.nextInt(alphabet.length());
        }
        return generatedString;
    }
}
