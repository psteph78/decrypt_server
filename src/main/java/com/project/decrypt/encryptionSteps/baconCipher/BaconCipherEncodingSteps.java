package com.project.decrypt.encryptionSteps.baconCipher;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaconCipherEncodingSteps {
    private List<Pair<Character, String>> encryptionSteps = new ArrayList<>();

    public List<Pair<Character, String>> getEncryptionSteps() {
        return encryptionSteps;
    }

    public BaconCipherEncodingSteps (final String plainText, final Character firstCharacter, final Character secondCharacter) {
        Map<Character, String> baconBinaryMap = BaconCipherBinaryEncoding.replacedBinaryLetterMap(firstCharacter, secondCharacter);
        final String textToEncode = plainText.toUpperCase();
        for (int i = 0; i < textToEncode.length(); i++) {
            if (Character.isUpperCase(textToEncode.charAt(i))) {
                final Pair<Character, String> letterEncryption = new Pair<>(textToEncode.charAt(i), baconBinaryMap.get(textToEncode.charAt(i)));
                encryptionSteps.add(letterEncryption);
            }
        }
    }
}
