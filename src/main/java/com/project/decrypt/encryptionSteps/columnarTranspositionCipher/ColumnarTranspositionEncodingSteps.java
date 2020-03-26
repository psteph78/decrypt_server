package com.project.decrypt.encryptionSteps.columnarTranspositionCipher;

import com.project.decrypt.cipherHelpers.ColumnarTranspositionCipherHelper;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ColumnarTranspositionEncodingSteps {
     private Integer[] permutationOrder;
     private Character[][] cipherMatrix;
     private List<Pair<Integer, String>> encodingSteps = new ArrayList<>();

    public ColumnarTranspositionEncodingSteps(final String plainText, final String keyword) {
        final String keywordFinal = keyword.toUpperCase();

        Integer nrColumns = keywordFinal.length();
        Integer nrRows = plainText.length() / nrColumns;
        if (nrRows % nrColumns != 0) {
            nrRows++;
        }

        // determine permutation order
        permutationOrder = ColumnarTranspositionCipherHelper.determinePemutationOrderForEncryptionStep(keywordFinal);

        // determine cipher matrix
        cipherMatrix = new Character[nrRows][nrColumns];
        ColumnarTranspositionCipherHelper.determineCipherMatrix(true, plainText, nrColumns, nrRows, cipherMatrix);

        // determine encoding steps
        final Map<Integer, Integer> mapToDetermineOrderOfLetters = ColumnarTranspositionCipherHelper.determineKeyPermutationOrder(keyword);
        // sort key permutation in alphabetical order
        List<Integer> permutationOrder = new ArrayList<Integer>(mapToDetermineOrderOfLetters.keySet());
        Collections.sort(permutationOrder);

        for(Integer alphabetOrder: permutationOrder) {
            final StringBuilder cipherOfColumn = new StringBuilder();
            Integer columnToRead = mapToDetermineOrderOfLetters.get(alphabetOrder);
            for (int i = 0; i < nrRows; i++){
                cipherOfColumn.append(cipherMatrix[i][columnToRead]);
            }
            encodingSteps.add(new Pair<>(alphabetOrder, cipherOfColumn.toString()));
        }
    }
}
