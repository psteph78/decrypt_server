package com.project.decrypt.cipherHelpers;

public class KeywordCipherHelper {

    public static String generateKey(final String keyword, final Character offSet) {
        // initialize a boolean array to track occurrence of alphabet letters
        final boolean[] letterOccurrence = new boolean[26];
        // initialize array for character that holds key
        final char[] key = new char[26];

        // calculate starting position
        int positionToInsert = Character.toUpperCase(offSet) - 65;

        final String keywordUppercase = keyword.toUpperCase();
        // insert the keyword starting from offSet
        positionToInsert = insertKeywordIntoKey(letterOccurrence, key, positionToInsert, keywordUppercase);

        //insert remaining letters
        insertRemainingAlphabetIntoKey(letterOccurrence, key, positionToInsert);

        return new String(key);
    }

    private static void insertRemainingAlphabetIntoKey(boolean[] letterOccurrence, char[] key, int positionToInsert) {
        for (int i = 0; i < 26; i++) {
            if (!letterOccurrence[i]) {
                if (positionToInsert == 26) {
                    positionToInsert = 0;
                }
                key[positionToInsert] = (char) (i + 65);
                positionToInsert++;
                letterOccurrence[i] = true;
            }
        }
    }

    private static int insertKeywordIntoKey(boolean[] letterOccurrence, char[] key, int positionToInsert,
                                            String keywordUppercase) {
        for (int i = 0; i < keywordUppercase.length(); i++) {
            if (keywordUppercase.charAt(i) >= 'A' && keywordUppercase.charAt(i) <= 'Z') {
                //check if already occurred
                if (!letterOccurrence[keywordUppercase.charAt(i) - 65]) {
                    letterOccurrence[keywordUppercase.charAt(i) - 65] = true;
                    // insert into position
                    if (positionToInsert != 25) {
                        key[positionToInsert] = keywordUppercase.charAt(i);
                        positionToInsert++;
                    } else {
                        key[positionToInsert] = keywordUppercase.charAt(i);
                        positionToInsert = 0;
                    }
                }
            }
        }
        return positionToInsert;
    }
}
