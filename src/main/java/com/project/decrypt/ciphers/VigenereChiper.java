package com.project.decrypt.ciphers;

import com.project.decrypt.cipherHelpers.EncryptionUtil;
import com.project.decrypt.cipherHelpers.VigenereCipherHelper;

public class VigenereChiper {

  public static String encrypt(final String plainText, final String keyword, final boolean ignoreCase,
      final boolean ignoreSpecialCharacters) {

    // remove special characters
    final String plainTextToEncrypt = EncryptionUtil.determinePlainTextToEncrypt(plainText, ignoreSpecialCharacters);

    final StringBuilder cipherText = new StringBuilder();
    final String key = VigenereCipherHelper.generateKey(plainText, keyword);

    for (int i = 0; i < plainTextToEncrypt.length(); i++) {
      if (Character.isUpperCase(plainTextToEncrypt.charAt(i))) {
        final char encryptedCharacter = (char) (((int) plainTextToEncrypt.charAt(i) + key.charAt(i)) % 26 + 65);
        cipherText.append(encryptedCharacter);
      } else if (Character.isLowerCase(plainTextToEncrypt.charAt(i))) {
        final char encryptedCharacter = (char) (((int) plainTextToEncrypt.charAt(i) + key.charAt(i)) % 26 + 97);
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
