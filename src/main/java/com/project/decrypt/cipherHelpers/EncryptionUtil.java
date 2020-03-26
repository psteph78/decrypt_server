package com.project.decrypt.cipherHelpers;

public class EncryptionUtil {

  public static String determinePlainTextToEncrypt(String plainText, boolean ignoreSpecialCharacters) {
    final String plainTextToEncrypt;
    if (ignoreSpecialCharacters) {
      // remove special characters from plainText
      plainTextToEncrypt = plainText.replaceAll("[^a-zA-Z0-9]+", "");
    } else {
      plainTextToEncrypt = plainText;
    }
    return plainTextToEncrypt;
  }
}
