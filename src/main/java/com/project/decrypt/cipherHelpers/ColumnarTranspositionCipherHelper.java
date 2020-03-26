package com.project.decrypt.cipherHelpers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ColumnarTranspositionCipherHelper {

    public static Map<Integer, Integer> determineKeyPermutationOrder(String key) {
        final Map<Integer, Integer> mapToDetermineOrderOfLetters = new HashMap<Integer, Integer>();
        for (int i = 0; i < key.length(); i++) {
            final char keyCharacter = key.charAt(i);
            if (Character.isUpperCase(keyCharacter)) {
                mapToDetermineOrderOfLetters.put(((int)key.charAt(i) - 65), i);
            } else {
                mapToDetermineOrderOfLetters.put(((int)key.charAt(i) - 97), i);
            }
        }
        return mapToDetermineOrderOfLetters;
    }

    public static Integer[] determinePemutationOrderForEncryptionStep(String key) {
        final Integer[] permutationOrder = new Integer[key.length()];
        //sort letters of keyword in alphabetical order
        final char[] sortedCharKey = key.toCharArray();
        Arrays.sort(sortedCharKey);
        final String sortedKeyWord = Arrays.toString(sortedCharKey);

        //start from 1, because those values will appear in front end
        int letterOrder = 1;
        //determine order of each letter based on alphabetical order
        for (int i = 0; i < sortedKeyWord.length(); i++) {
            if (Character.isUpperCase(sortedKeyWord.charAt(i))) {
                permutationOrder[key.indexOf(sortedKeyWord.charAt(i))] = letterOrder;
                letterOrder++;
            }
        }

        return permutationOrder;
    }

    public static void determineCipherMatrix(boolean ignoreCase, String plainTextToEncrypt, Integer nrColumns,
                                              Integer nrRows, Character[][] chiperMatrix) {
        int currentLetterPositionOfPlainText = 0;
        for (int i = 0; i < nrRows; i++) {
            for (int j = 0; j < nrColumns; j++) {
                if (currentLetterPositionOfPlainText < plainTextToEncrypt.length()) {
                    final Character charToAdd = plainTextToEncrypt.charAt(currentLetterPositionOfPlainText);
                    if (Character.isUpperCase(charToAdd)) {
                        chiperMatrix[i][j] = charToAdd;
                    } else if (Character.isLowerCase(charToAdd)) {
                        if (ignoreCase) {
                            chiperMatrix[i][j] = Character.toUpperCase(charToAdd);
                        } else {
                            chiperMatrix[i][j] = charToAdd;
                        }
                    } else {
                        chiperMatrix[i][j] = charToAdd;
                    }
                    currentLetterPositionOfPlainText++;
                } else {
                    // plainText has been added and there are blank spaces left in the matrix
                    // fill them up with random characters
                    if (ignoreCase) {
                        chiperMatrix[i][j] = Character.toUpperCase(chooseRandomLetter());
                    } else {
                        chiperMatrix[i][j] = chooseRandomLetter();
                    }
                }
            }
        }
    }

    private static Character chooseRandomLetter() {
        Random random = new Random();
        return (char) (random.nextInt(26) + 'a');
    }
}
