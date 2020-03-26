package com.project.decrypt.ciphers;

import com.project.decrypt.cipherHelpers.EncryptionUtil;
import com.project.decrypt.cipherHelpers.KeywordCipherHelper;

public class KeywordCipher {

  public static String encrypt(final String plainText, final String keyword, final Character offSet,
      final boolean ignoreCase, final boolean ignoreSpecialCharacters) {

    // remove special characters
    final String plainTextToEncrypt = EncryptionUtil.determinePlainTextToEncrypt(plainText, ignoreSpecialCharacters);

    final StringBuilder cipherText = new StringBuilder();
    final String key = KeywordCipherHelper.generateKey(keyword, offSet);

    for (int i = 0; i < plainTextToEncrypt.length(); i++) {
      if (Character.isUpperCase(plainTextToEncrypt.charAt(i))) {
        final char encryptedCharacter = key.charAt(((int) plainTextToEncrypt.charAt(i)) - 65);
        cipherText.append(encryptedCharacter);
      } else if (Character.isLowerCase(plainTextToEncrypt.charAt(i))) {
        final char encryptedCharacter = key.charAt(((int) plainTextToEncrypt.charAt(i)) - 97);
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
