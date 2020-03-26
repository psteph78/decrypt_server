package com.project.decrypt.encryptionSteps.trifidCipher;

import com.project.decrypt.cipherHelpers.TrifidCipherHelper;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrifidCipherEncryptionSteps {
    private Character[][] firstLayer = new Character[3][3];
    private Character[][] secondLayer = new Character[3][3];
    private Character[][] thirdLayer = new Character[3][3];

    private Map<Character, Integer> letterEncryption = new HashMap<>();
    private Map<Integer, Character> letterDecryption = new HashMap<>();

    private List<List<Pair<Character, String>>> encryptionGroups = new ArrayList<>();
    private List<Pair<Character, String>> lastEncryptionGroup = new ArrayList<>();

    public Character[][] getFirstLayer() {
        return firstLayer;
    }

    public Character[][] getSecondLayer() {
        return secondLayer;
    }

    public Character[][] getThirdLayer() {
        return thirdLayer;
    }

    public Map<Character, Integer> getLetterEncryption() {
        return letterEncryption;
    }

    public Map<Integer, Character> getLetterDecryption() {
        return letterDecryption;
    }

    public List<List<Pair<Character, String>>> getEncryptionGroups() {
        return encryptionGroups;
    }

    public List<Pair<Character, String>> getLastEncryptionGroup() {
        return lastEncryptionGroup;
    }

    public TrifidCipherEncryptionSteps(final String plainText, final String keyword, final Integer groupSize) {
        final String keywordToUpperCase = keyword.toUpperCase();
        TrifidCipherHelper.fillLayers(firstLayer, secondLayer, thirdLayer, keywordToUpperCase);
        TrifidCipherHelper.determineLetterEncryptionAndDecryption(letterEncryption, letterDecryption,
                firstLayer, secondLayer, thirdLayer);


        final String plainTextToUpperCase = plainText.toUpperCase();
        final Integer encryptionMatrixSize = plainText.length() / groupSize;
        final Integer[][] groupEncryptionMatrix = new Integer[3][encryptionMatrixSize * groupSize];
        TrifidCipherHelper.fillGrEncryptionMatrixWithPlainText(groupSize, encryptionMatrixSize,
                groupEncryptionMatrix, plainTextToUpperCase, letterEncryption);

        int letterPosition = 0;
        // determine encipherment pairs from encryption groups
        List<Pair<Character, String>> encryptionGroup = new ArrayList<>();
        for(int col = 0; col < encryptionMatrixSize * groupSize; col++) {
            final StringBuilder letterEncoding = new StringBuilder();
            for (int row = 0; row < 3; row++) {
                letterEncoding.append(groupEncryptionMatrix[row][col]);
            }
            encryptionGroup.add(new Pair<>(plainTextToUpperCase.charAt(letterPosition), letterEncoding.toString()));
            if (encryptionGroup.size() == groupSize) {
                encryptionGroups.add(encryptionGroup);
                encryptionGroup = new ArrayList<>();
            }
            letterPosition++;
        }

        // determine encipherment pairs from last encryption group
        final int leftLetters = plainTextToUpperCase.length() % groupSize;
        final Integer[][] encryptionMatrixRemainingLetters = TrifidCipherHelper.
                determineMatrixForRemainingLetters(plainTextToUpperCase, letterEncryption, leftLetters);

        for(int col = 0; col < leftLetters; col++) {
            final StringBuilder letterEncoding = new StringBuilder();
            for (int row = 0; row < 3; row++) {
                letterEncoding.append(encryptionMatrixRemainingLetters[row][col]);
            }
            lastEncryptionGroup.add(new Pair<>(plainTextToUpperCase.charAt(letterPosition), letterEncoding.toString()));
            letterPosition++;
        }
    }
}
