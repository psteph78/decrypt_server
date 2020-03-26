package com.project.decrypt.encryptionSteps.ceasarCipher;

import java.util.ArrayList;
import java.util.List;

public class CeasarCipherShifting {
    private Character[] normalAlphabet = new Character[26];
    private List<Character[]> alphabetShifts = new ArrayList<>();

    public Character[] getNormalAlphabet() {
        return normalAlphabet;
    }

    public List<Character[]> getAlphabetShifts() {
        return alphabetShifts;
    }

    public CeasarCipherShifting(final Integer numberOfShifts){
        for (int i = 0; i < 26; i++){
            normalAlphabet[i] = (char)(i+65);
        }
        alphabetShifts.add(normalAlphabet);

        for (int i = 0; i < numberOfShifts; i++) {
            determineAlphabetAfterOneShift(i);
        }
    }

    private void determineAlphabetAfterOneShift(final Integer lastAlphabetPos){
        Character[] newShift = new Character[26];
        System.arraycopy(alphabetShifts.get(lastAlphabetPos), 0, newShift, 1, 25);
        // put first letter (last letter from previous alphabet shift
        newShift[0] = alphabetShifts.get(lastAlphabetPos)[25];
        alphabetShifts.add(newShift);
    }
}
