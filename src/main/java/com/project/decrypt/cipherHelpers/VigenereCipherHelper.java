package com.project.decrypt.cipherHelpers;

public class VigenereCipherHelper {

    /**
     * Generate key in cyclic manner given plainText and keyword. Keyword is repeated until it reaches the length of the
     * plain text.
     *
     * @return key of length equal to plainText
     */
    public static String generateKey(final String plainText, final String keyword) {
        final StringBuilder keyBuilder = new StringBuilder(keyword.toUpperCase());
        for (int i = 0; ; i++) {
            // if keyword is longer than plainText, return substring from keyword
            // of length of plainText
            if (keyBuilder.length() > plainText.length()) {
                return keyBuilder.substring(0, plainText.length());
            }
            if (keyBuilder.length() == plainText.length()) {
                break;
            }
            keyBuilder.append(keyBuilder.charAt(i));
        }
        return keyBuilder.toString();
    }
}
