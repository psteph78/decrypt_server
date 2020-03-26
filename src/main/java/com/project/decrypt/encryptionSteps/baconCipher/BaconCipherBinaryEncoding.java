package com.project.decrypt.encryptionSteps.baconCipher;

import java.util.Map;
import java.util.TreeMap;

public class BaconCipherBinaryEncoding {
    private final static String addZeros = "0000";

    public static Map<Character, String> binaryLetterMap(){
        Map<Character, String> binaryLetterMap = new TreeMap<>();
        for (int i = 0; i < 26; i++) {
            final String binaryValue = addZeros.concat(Integer.toString(i, 2));
            final String encryptionPattern = binaryValue.substring(binaryValue.length() - 5);
            final Character letter = (char) (i + 65);
            binaryLetterMap.put(letter, encryptionPattern);
        }
        return binaryLetterMap;
    }

    public static Map<Character, String> replacedBinaryLetterMap(Character firstLetter, Character secondLetter) {
        Map<Character, String> binaryLetterMap = BaconCipherBinaryEncoding.binaryLetterMap();
        Map<Character, String> replacedBinaryMap = new TreeMap<>();
        for (Map.Entry<Character, String> entry: binaryLetterMap.entrySet()) {
            final StringBuilder encryptedLetter = new StringBuilder();
            for (Character c : entry.getValue().toCharArray()){
                if (c.equals('0')){
                    encryptedLetter.append(firstLetter);
                } else {
                    encryptedLetter.append(secondLetter);
                }
            }
            replacedBinaryMap.put(entry.getKey(), encryptedLetter.toString());
        }
        return replacedBinaryMap;
     }
}
