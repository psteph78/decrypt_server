package com.project.decrypt.encryptionSteps.railFenceCipher;

import com.project.decrypt.cipherHelpers.EncryptionUtil;
import com.project.decrypt.cipherHelpers.RailFenceCipherHelper;

import java.util.List;

public class RailFenceCipherEncryptionSteps {
    private List<Character[][]> railFenceCipherSteps;

    public List<Character[][]> getRailFenceCipherSteps() {
        return railFenceCipherSteps;
    }

    public RailFenceCipherEncryptionSteps(final String plainText, final int nrRails) {
        final String plainTextToEncrypt = EncryptionUtil.determinePlainTextToEncrypt(plainText, true).toUpperCase();

        final Character[][] railFenceMatrix = new Character[nrRails][plainTextToEncrypt.length()];
        RailFenceCipherHelper.initializeMatrixWithDots(nrRails, plainTextToEncrypt, railFenceMatrix);
        railFenceCipherSteps = RailFenceCipherHelper.fillMatrixWithPlainText(nrRails, plainTextToEncrypt, railFenceMatrix);
    }
}
