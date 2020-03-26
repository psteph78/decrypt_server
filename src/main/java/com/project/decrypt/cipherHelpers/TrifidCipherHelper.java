package com.project.decrypt.cipherHelpers;

import java.util.Map;

public class TrifidCipherHelper {


    public static void fillLayers(Character[][] firstLayer, Character[][] secondLayer, Character[][] thirdLayer, final String keyword) {
        initializeLayersWithBlankSpaces(firstLayer, secondLayer, thirdLayer);
        final boolean[] letterOccurrence = new boolean[26];
        insertKeywordIntoLayers(firstLayer, secondLayer, thirdLayer, letterOccurrence, keyword);
        insertAlphabetIntoLayer(firstLayer, secondLayer, thirdLayer, letterOccurrence);
        thirdLayer[2][2] = '+';
    }

    public static void determineLetterEncryptionAndDecryption(final Map<Character, Integer> letterEncryption,
                                                              final Map<Integer, Character> letterDecryption, final Character[][] firstLayer, final Character[][] secondLayer,
                                                              final Character[][] thirdLayer) {
        determineLetterEncodingBasedOnLayers(letterEncryption, letterDecryption, firstLayer, 1);
        determineLetterEncodingBasedOnLayers(letterEncryption, letterDecryption, secondLayer, 2);
        determineLetterEncodingBasedOnLayers(letterEncryption, letterDecryption, thirdLayer, 3);

    }

    private static void determineLetterEncodingBasedOnLayers(final Map<Character, Integer> letterEncryption,
                                                             final Map<Integer, Character> letterDecryption,
                                                             final Character[][] layer, final Integer layerNumber) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final Integer determineNumberEncryption = Integer
                        .valueOf(String.valueOf(layerNumber).concat(String.valueOf(row + 1).concat(String.valueOf(col + 1))));
                letterEncryption.put(layer[row][col], determineNumberEncryption);
                letterDecryption.put(determineNumberEncryption, layer[row][col]);

            }
        }
    }

    private static void initializeLayersWithBlankSpaces(final Character[][] firstLayer, final Character[][] secondLayer,
                                                        final Character[][] thirdLayer) {
        initializeLayerWithBlankSpaces(firstLayer);
        initializeLayerWithBlankSpaces(secondLayer);
        initializeLayerWithBlankSpaces(thirdLayer);
    }

    private static void initializeLayerWithBlankSpaces(final Character[][] layer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                layer[i][j] = '\n';
            }
        }
    }

    private static void insertKeywordIntoLayers(Character[][] firstLayer, Character[][] secondLayer,
                                                Character[][] thirdLayer, boolean[] letterOccurrance, String keywordUppercase) {
        for (int i = 0; i < keywordUppercase.length(); i++) {
            final Character letterToInsert = keywordUppercase.charAt(i);
            // check if already occurred
            if (!letterOccurrance[letterToInsert - 65]) {
                // insert into layer and position
                letterOccurrance[letterToInsert - 65] = true;
                determineLayerForInsertion(firstLayer, secondLayer, thirdLayer, letterToInsert);
            }
        }
    }

    private static void determineLayerForInsertion(Character[][] firstLayer, Character[][] secondLayer,
                                                   Character[][] thirdLayer, char letterToInsert) {
        if (!insertLetterIntoLayer(firstLayer, letterToInsert)) {
            if (!insertLetterIntoLayer(secondLayer, letterToInsert)) {
                insertLetterIntoLayer(thirdLayer, letterToInsert);
            }
        }
    }

    private static boolean insertLetterIntoLayer(Character[][] layer, Character letterToInsert) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (row == 2 && col == 2 && layer[row][col] != '\n') {
                    return false;
                }
                if (layer[row][col] == '\n') {
                    layer[row][col] = letterToInsert;
                    return true;
                }
            }
        }
        return true;
    }

    private static void insertAlphabetIntoLayer(Character[][] firstLayer, Character[][] secondLayer,
                                                Character[][] thirdLayer, boolean[] letterOccurrance) {
        for (int i = 0; i < 26; i++) {
            if (!letterOccurrance[i]) {
                // insert into layer and position
                letterOccurrance[i] = true;
                final char letterToInsert = (char) (i + 65);
                determineLayerForInsertion(firstLayer, secondLayer, thirdLayer, letterToInsert);
            }
        }
    }

    /**
     * Fills plain text into group encryption matrix. Each column represents a three-digit number sequence corresponding
     * to the letter that is to be encrypted.
     */
    public static void fillGrEncryptionMatrixWithPlainText(Integer groupSize, Integer encryptionMatrixSize,
                                                           Integer[][] groupEncryptionMatrix, String plainTextToEncrypt, Map<Character, Integer> letterEncryption) {

        final String numberSequenceOfPlainText = plainTextToNumbers(plainTextToEncrypt, letterEncryption);
        int position = 0;
        for (int col = 0; col < encryptionMatrixSize * groupSize; col++) {
            for (int row = 0; row < 3; row++) {
                groupEncryptionMatrix[row][col] = Character.getNumericValue(numberSequenceOfPlainText.charAt(position));
                position++;
            }
        }
    }

    // transform plainText to string of corresponding encipherment numbers
    private static String plainTextToNumbers(final String plainText, final Map<Character, Integer> letterEncryption) {
        StringBuilder plainTextEncryption = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            plainTextEncryption.append(letterEncryption.get(plainText.charAt(i)));
        }
        return plainTextEncryption.toString();
    }

    /**
     * Determines an encryption matrix for remaining letters -> letters, that did not fit into a group. Eg. if group size
     * is 5 and the length of the plain text is 8, 3 letters will be left out and will constitute a new group.
     */
    public static Integer[][] determineMatrixForRemainingLetters(String plainTextToEncrypt, Map<Character, Integer> letterEncryption, final int leftLetters) {
        final String numberSequenceOfPlainText = plainTextToNumbers(plainTextToEncrypt, letterEncryption);

        final Integer[][] encryptionMatrixRemainingLetters = new Integer[3][leftLetters * 3];
        int positionRemainingLetters = numberSequenceOfPlainText.length() - (leftLetters * 3);
        for (int j = 0; j < leftLetters; j++) {
            for (int i = 0; i < 3; i++) {
                encryptionMatrixRemainingLetters[i][j] = Character
                        .getNumericValue(numberSequenceOfPlainText.charAt(positionRemainingLetters));
                positionRemainingLetters++;
            }
        }
        return encryptionMatrixRemainingLetters;
    }

}
