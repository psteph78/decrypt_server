package com.project.decrypt.cipherHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RailFenceCipherHelper {

    public static List<Character[][]> fillMatrixWithPlainText(final int nrRails, final String plainTextToEncrypt, Character[][] railFenceMatrix) {
        // used for determining each step of the filling-matrix process
        List<Character[][]> railFenceCipherFillingSteps = new ArrayList<>();
        boolean isDirectionDown = false;
        int row = 0;
        int column = 0;

        for (int i = 0; i < plainTextToEncrypt.length(); i++) {
            // check direction of flow
            // reverse direction if top or bottom has been reached
            if (row == 0 || row == nrRails - 1) {
                isDirectionDown = !isDirectionDown;
            }

            // fill corresponding letter into matrix
            railFenceMatrix[row][column++] = plainTextToEncrypt.charAt(i);
            railFenceCipherFillingSteps.add(captureFillingMatrixStep(nrRails, railFenceMatrix));

            // find next row using the direction flag
            if (isDirectionDown) {
                row++;
            } else {
                row--;
            }
        }
        return railFenceCipherFillingSteps;
    }

    private static Character[][] captureFillingMatrixStep(int nrRails, Character[][] railFenceMatrix) {
        Character[][] copiedMatrix = new Character[nrRails][];
        for (int i = 0;  i < nrRails; i++) {
            copiedMatrix[i] = Arrays.copyOf(railFenceMatrix[i], railFenceMatrix[i].length);
        }
        return copiedMatrix;
    }

    public static void initializeMatrixWithBlankSpaces(int nrRails, final String plainTextToEncrypt,
                                                        Character[][] railFenceMatrix) {
        for (int i = 0; i < nrRails; i++) {
            for (int j = 0; j < plainTextToEncrypt.length(); j++) {
                railFenceMatrix[i][j] = '\n';
            }
        }
    }

    public static void initializeMatrixWithDots(int nrRails, final String plainTextToEncrypt,
                                                       Character[][] railFenceMatrix) {
        for (int i = 0; i < nrRails; i++) {
            for (int j = 0; j < plainTextToEncrypt.length(); j++) {
                railFenceMatrix[i][j] = '.';
            }
        }
    }
}
