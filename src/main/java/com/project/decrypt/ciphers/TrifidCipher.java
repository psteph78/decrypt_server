package com.project.decrypt.ciphers;

import com.project.decrypt.cipherHelpers.EncryptionUtil;
import com.project.decrypt.cipherHelpers.TrifidCipherHelper;

import java.util.HashMap;
import java.util.Map;

public class TrifidCipher {

  public static String encrypt(final String plainText, final String key, final boolean ignoreCase,
      final boolean ignoreSpecialCharacters, final Integer groupSize) {

    // remove special characters from plain text
    final String plainTextToEncrypt = EncryptionUtil.determinePlainTextToEncrypt(plainText, true)
        .toUpperCase();
    // transform keyword into uppercase letters
    final String keywordUppercase = key.toUpperCase();

    // initialize layers
    final Character[][] firstLayer = new Character[3][3];
    final Character[][] secondLayer = new Character[3][3];
    final Character[][] thirdLayer = new Character[3][3];

    //fill layers with keyword and the rest of the alphabet
    TrifidCipherHelper.fillLayers(firstLayer, secondLayer, thirdLayer, keywordUppercase);

    // used to transform letters into number codes
    final Map<Character, Integer> letterEncryption = new HashMap<Character, Integer>();
    // used to transform number codes into encrypted letter
    final Map<Integer, Character> letterDecryption = new HashMap<Integer, Character>();
    //determine encryption and decryption maps
    TrifidCipherHelper.determineLetterEncryptionAndDecryption(letterEncryption, letterDecryption, firstLayer, secondLayer, thirdLayer);

    // determine size of matrix for encryption of plaintext
    final Integer encryptionMatrixSize = plainTextToEncrypt.length() / groupSize;
    // declare encryption matrix
    final Integer[][] groupEncryptionMatrix = new Integer[3][encryptionMatrixSize * groupSize];

    // fill in group encryption matrix with plain text
    TrifidCipherHelper.fillGrEncryptionMatrixWithPlainText(groupSize, encryptionMatrixSize, groupEncryptionMatrix,
        plainTextToEncrypt, letterEncryption);

    // determine cipher text based on group encryption matrix
    StringBuilder cipherText = new StringBuilder();
    determineCipherTextFromEncryptionMatrix(groupSize, plainTextToEncrypt, letterDecryption, encryptionMatrixSize,
        groupEncryptionMatrix, cipherText);

    // fill in letters of plain text that were left (did not fit into a group matrix of group size)
    final int leftLetters = plainTextToEncrypt.length() % groupSize;
    // if there is only one letter left; don't encrypt it
    if (leftLetters == 1) {
      cipherText.append(plainTextToEncrypt.charAt(plainTextToEncrypt.length() - 1));
    }
    final Integer[][] encryptionMatrixRemainingLetters = TrifidCipherHelper.determineMatrixForRemainingLetters(plainTextToEncrypt,
        letterEncryption, leftLetters);
    // determine cipher text based on encryptionMatrix of remaining letters
    determineCipherTextForGroup(0, letterDecryption, leftLetters - 1, encryptionMatrixRemainingLetters, cipherText);


    String finalCipherText = cipherText.toString();
    // modify to sensitive case text if option was chosen
    if (!ignoreCase) {
      finalCipherText = removeIgnoreCaseFromCipherText(plainText, finalCipherText);
    }
    // add the special characters from plain text to cipher text if this option has been chosen
    if (!ignoreSpecialCharacters) {
      finalCipherText = integrateSpecialCharactersFromPlainText(plainText, finalCipherText);
    }

    return finalCipherText;
  }

  private static String removeIgnoreCaseFromCipherText(final String plainText, String cipherText) {
    int positionToModifyInCipherText = 0;
    for (int i = 0; i < plainText.length(); i++) {
      if (Character.isLowerCase(plainText.charAt(i))) {
        String toAppend = "";
        if (i + 1 < plainText.length()) {
          toAppend = cipherText.substring(positionToModifyInCipherText + 1);
        }
        cipherText = cipherText.substring(0, positionToModifyInCipherText)
            .concat(String.valueOf(Character.toLowerCase(cipherText.charAt(positionToModifyInCipherText))))
            .concat(toAppend);
        positionToModifyInCipherText++;
      }
      if (Character.isUpperCase(plainText.charAt(i))) {
        String toAppend = "";
        if (i + 1 < plainText.length()) {
          toAppend = cipherText.substring(positionToModifyInCipherText + 1);
        }
        cipherText = cipherText.substring(0, positionToModifyInCipherText)
            .concat(String.valueOf(cipherText.charAt(positionToModifyInCipherText))).concat(toAppend);
        positionToModifyInCipherText++;
      }
    }
    return cipherText;
  }

  private static String integrateSpecialCharactersFromPlainText(final String plainText, String cipherText) {
    for (int i = 0; i < plainText.length(); i++) {
      if (!Character.isUpperCase(plainText.charAt(i)) && !Character.isLowerCase(plainText.charAt(i))) {
        cipherText = cipherText.substring(0, i).concat(String.valueOf(plainText.charAt(i)))
            .concat(cipherText.substring(i));
      }
    }
    return cipherText;
  }


  /**
   * Determines cipher text based on the group encryption matrix. A three-digit character sequence is read along the
   * rows of the matrix, this will be then transformed in the corresponding letter from the letterDecryption Map. Column
   * blocks are build based on the size of the groups (groupSize param) from which the number sequence are being read.
   */
  private static void determineCipherTextFromEncryptionMatrix(Integer groupSize, String plainTextToEncrypt,
      Map<Integer, Character> letterDecryption, Integer encryptionMatrixSize, Integer[][] groupEncryptionMatrix,
      StringBuilder cipherText) {
    Integer startingPosition = 0;
    for (int i = 0; i < plainTextToEncrypt.length() / groupSize; i++) {
      determineCipherTextForGroup(startingPosition, letterDecryption, encryptionMatrixSize, groupEncryptionMatrix,
          cipherText);
      startingPosition = startingPosition + groupSize;
    }
  }

  private static void determineCipherTextForGroup(Integer startingPosition, Map<Integer, Character> letterDecryption,
      Integer encryptionMatrixSize, Integer[][] encryptionMatrix, StringBuilder cipherText) {
    StringBuilder cipherLetterBuilder = new StringBuilder();
    for (int i = 0; i < 3; i++) {
      for (int j = startingPosition; j < encryptionMatrixSize + startingPosition + 1; j++) {
        cipherLetterBuilder.append(encryptionMatrix[i][j]);
        if (cipherLetterBuilder.length() == 3) {
          cipherText.append(letterDecryption.get(Integer.valueOf(cipherLetterBuilder.toString())));
          cipherLetterBuilder = new StringBuilder();
        }
      }
    }
  }
}