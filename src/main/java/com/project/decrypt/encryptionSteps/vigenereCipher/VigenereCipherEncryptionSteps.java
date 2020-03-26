package com.project.decrypt.encryptionSteps.vigenereCipher;

import com.project.decrypt.cipherHelpers.EncryptionUtil;
import com.project.decrypt.cipherHelpers.VigenereCipherHelper;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class VigenereCipherEncryptionSteps {
    private String cycledKey;
    private List<Pair<Character, Character>> encryptionSteps = new ArrayList<>();

    public String getCycledKey() {
        return cycledKey;
    }

    public List<Pair<Character, Character>> getEncryptionSteps() {
        return encryptionSteps;
    }

    public VigenereCipherEncryptionSteps(final String plainText, final String keyword) {
        final String plainTextToEncrypt = EncryptionUtil.determinePlainTextToEncrypt(plainText, true).toUpperCase();
        cycledKey = VigenereCipherHelper.generateKey(plainTextToEncrypt, keyword);

        // determine encryption steps
        for (int i = 0; i < plainTextToEncrypt.length(); i++) {
            final Character encryptedLetter = (char) (((int) plainTextToEncrypt.charAt(i) + cycledKey.charAt(i)) % 26 + 65);
            encryptionSteps.add(new Pair<>(plainTextToEncrypt.charAt(i), encryptedLetter));
        }

    }

}
