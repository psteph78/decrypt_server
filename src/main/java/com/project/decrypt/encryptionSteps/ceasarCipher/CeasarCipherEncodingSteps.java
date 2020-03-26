package com.project.decrypt.encryptionSteps.ceasarCipher;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class CeasarCipherEncodingSteps {
    private List<Pair<Character, Character>> encryptionSteps = new ArrayList<>();

    public List<Pair<Character, Character>> getEncryptionSteps() {
        return encryptionSteps;
    }

    public CeasarCipherEncodingSteps(final Integer nrShifts, final String plainText) {
        CeasarCipherShifting ceasarCipherShifting = new CeasarCipherShifting(nrShifts);
        final Character[] normalAlphabet = ceasarCipherShifting.getNormalAlphabet();
        final Character[] encryptionAlphabet = ceasarCipherShifting.getAlphabetShifts().get(ceasarCipherShifting.getAlphabetShifts().size() - 1);
        for (int i = 0; i < plainText.length(); i++) {
            //find position of letter in normal alphabet
            if (Character.isUpperCase(plainText.charAt(i)) | Character.isLowerCase(plainText.charAt(i))) {
                final int letterPosition = determineLetterPosition(plainText.charAt(i), normalAlphabet);
                final Pair<Character, Character> letterEncryption = new Pair<>(normalAlphabet[letterPosition], encryptionAlphabet[letterPosition]);
                encryptionSteps.add(letterEncryption);
            }
        }
    }

    private int determineLetterPosition(final Character letter, final Character[] alphabet) {
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] == Character.toUpperCase(letter)) {
                return i;
            }
        }
        // should never get here
        return -1;
    }
}
