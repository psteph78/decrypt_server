package com.project.decrypt.ciphers;

import com.project.decrypt.cipherHelpers.ColumnarTranspositionCipherHelper;
import com.project.decrypt.cipherHelpers.EncryptionUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ColumnarTranspositionCipher {

  public static String encrypt(final String plainText, final String key, final boolean ignoreCase,
      final boolean ignoreSpecialCharacters) {

    // remove special characters from plain text
    final String plainTextToEncrypt = EncryptionUtil.determinePlainTextToEncrypt(plainText, ignoreSpecialCharacters);

    Integer nrColumns = key.length();
    Integer nrRows = plainTextToEncrypt.length() / nrColumns;
    if (nrRows % nrColumns != 0) {
      nrRows++;
    }

    // fill in cipherMatrix with plainText
    final Character[][] chiperMatrix = new Character[nrRows][nrColumns];
    ColumnarTranspositionCipherHelper.determineCipherMatrix(ignoreCase, plainTextToEncrypt, nrColumns, nrRows, chiperMatrix);

    // determine key permutation order
    final Map<Integer, Integer> mapToDetermineOrderOfLetters = ColumnarTranspositionCipherHelper.determineKeyPermutationOrder(key);

    // sort key permutation in alphabetical order
    List<Integer> permutationOrder = new ArrayList<Integer>(mapToDetermineOrderOfLetters.keySet());
    Collections.sort(permutationOrder);


    // read cipher from matrix
    final StringBuilder cipherText = new StringBuilder();

    for(Integer alphabetOrder: permutationOrder) {
      Integer columnToRead = mapToDetermineOrderOfLetters.get(alphabetOrder);
      for (int i = 0; i < nrRows; i++){
        cipherText.append(chiperMatrix[i][columnToRead]);
      }
    }
    return cipherText.toString();
  }
}
