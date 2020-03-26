package com.project.decrypt.ciphers;

import com.project.decrypt.cipherHelpers.EncryptionUtil;

import java.util.HashMap;
import java.util.Map;

public class BaconCipher {
  private final static String addZeros = "0000";

  public static String encrypt(final String plainText, final Character firstChar, final Character secondChar,
      final boolean ignoreSpecialCharacters) {

    // remove special characters from plain text
    final String plainTextToEncrypt = EncryptionUtil.determinePlainTextToEncrypt(plainText, ignoreSpecialCharacters)
        .toUpperCase();

    //build encryption map
    Map<Character, String> encryptionMap = buildEncryptionMap(firstChar, secondChar);

    final StringBuilder cipherText = new StringBuilder();
    for (int i = 0; i < plainTextToEncrypt.length(); i++) {
      if (Character.isUpperCase(plainTextToEncrypt.charAt(i))) {
        cipherText.append(encryptionMap.get(plainTextToEncrypt.charAt(i))).append(" ");
      } else if (plainTextToEncrypt.charAt(i) != ' '){
        cipherText.append(plainTextToEncrypt.charAt(i));
      }
    }

    return cipherText.toString();
  }

  private static Map<Character, String> buildEncryptionMap(Character firstChar, Character secondChar) {
    Map<Character, String> encryptionMap = new HashMap<Character, String>();
    for (int i = 0; i < 26; i++) {
      final String binaryValue = addZeros.concat(Integer.toString(i, 2));
      final String encryptionPattern = binaryValue.substring(binaryValue.length() - 5);
      final StringBuilder encryptedLetter = new StringBuilder();
      for (Character c : encryptionPattern.toCharArray()) {
        if (c.equals('0')) {
          encryptedLetter.append(Character.toLowerCase(firstChar));
        } else {
          encryptedLetter.append(Character.toLowerCase(secondChar));
        }
      }
      encryptionMap.put((char) (i + 'A'), encryptedLetter.toString());
    }
    return encryptionMap;
  }


}

