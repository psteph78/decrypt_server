package com.project.decrypt.ciphers;

import com.project.decrypt.cipherHelpers.EncryptionUtil;
import com.project.decrypt.cipherHelpers.RailFenceCipherHelper;

public class RailFenceCipher {

  public static String encrypt(final String plainText, final int nrRails, final boolean ignoreCase,
      final boolean ignoreSpecialCharacters) {

    // remove special characters
    final String plainTextToEncrypt = EncryptionUtil.determinePlainTextToEncrypt(plainText, ignoreSpecialCharacters);

    // create rail fence matrix with nrRails = nr Rows and length of plainText = nrColumns
    final Character[][] railFenceMatrix = new Character[nrRails][plainTextToEncrypt.length()];

    // fill matrix to later distinguish filled spaces from blank ones
    RailFenceCipherHelper.initializeMatrixWithBlankSpaces(nrRails, plainTextToEncrypt, railFenceMatrix);

    // fill matrix with plain text characters
    RailFenceCipherHelper.fillMatrixWithPlainText(nrRails, plainTextToEncrypt, railFenceMatrix);

    // construct cipher from matrix
    final StringBuilder cipherText = new StringBuilder();

    for (int i = 0; i < nrRails; i++) {
      for (int j = 0; j < plainTextToEncrypt.length(); j++) {
        if (railFenceMatrix[i][j] != '\n') {
          if (ignoreCase) {
            cipherText.append(Character.toUpperCase(railFenceMatrix[i][j]));
          } else {
            cipherText.append(railFenceMatrix[i][j]);
          }
        }
      }
    }

    return cipherText.toString();
  }
}
