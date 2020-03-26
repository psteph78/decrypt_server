package com.project.decrypt.ciphers;

import com.project.decrypt.cipherHelpers.EncryptionUtil;

public class CeasarCipher {

  public static String encrypt(final String plainText, final Integer nrShifts, final boolean ignoreCase,
      final boolean ignoreSpecialCharacters) {

    // remove special characters from plain text
    final String plainTextToEncrypt = EncryptionUtil.determinePlainTextToEncrypt(plainText, ignoreSpecialCharacters);

    final StringBuilder cipherText = new StringBuilder();

    for (int i = 0; i < plainTextToEncrypt.length(); i++) {
      if (Character.isUpperCase(plainTextToEncrypt.charAt(i))) {
        final char encryptedCharacter = (char) (((int) plainTextToEncrypt.charAt(i) + nrShifts - 65) % 26 + 65);
        cipherText.append(encryptedCharacter);
      } else if (Character.isLowerCase(plainTextToEncrypt.charAt(i))) {
        final char encryptedCharacter = (char) (((int) plainTextToEncrypt.charAt(i) + nrShifts - 97) % 26 + 97);
        if (ignoreCase) {
          cipherText.append(Character.toUpperCase(encryptedCharacter));
        } else {
          cipherText.append(encryptedCharacter);
        }
      } else {
        cipherText.append(plainTextToEncrypt.charAt(i));
      }
    }
    return cipherText.toString();
  }
}
