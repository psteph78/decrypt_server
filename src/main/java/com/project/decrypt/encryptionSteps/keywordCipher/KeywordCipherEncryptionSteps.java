package com.project.decrypt.encryptionSteps.keywordCipher;

import com.project.decrypt.cipherHelpers.EncryptionUtil;
import com.project.decrypt.cipherHelpers.KeywordCipherHelper;
import javafx.util.Pair;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class KeywordCipherEncryptionSteps {
    private Character[] normalAlphabet = new Character[26];
    private Character[] encryptionAlphabet;
    private List<Pair<Character, Character>> encryptionSteps = new ArrayList<>();

    public Character[] getNormalAlphabet() {
        return normalAlphabet;
    }

    public Character[] getEncryptionAlphabet() {
        return encryptionAlphabet;
    }

    public List<Pair<Character, Character>> getEncryptionSteps() {
        return encryptionSteps;
    }

    public KeywordCipherEncryptionSteps(final String keyword, final Character offSet, final String plainText) {
        for (int i = 0; i < 26; i++){
            normalAlphabet[i] = (char)(i+65);
        }

        //encryption alphabet
        final String key = KeywordCipherHelper.generateKey(keyword, offSet);
        encryptionAlphabet = ArrayUtils.toObject(key.toCharArray());

        //plain text encryption steps
        final String plainTextToEncrypt = EncryptionUtil.determinePlainTextToEncrypt(plainText, true).toUpperCase();
        for (int i = 0; i < plainTextToEncrypt.length(); i++) {
            encryptionSteps.add(new Pair<>(plainTextToEncrypt.charAt(i), encryptionAlphabet[i]));
        }

    }
}
