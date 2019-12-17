package com.sap.ase;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicChecker {
    public boolean isIsomorphic(String s, String t) {
        if (!valid(s, t)) return false;

        Map<String, String> charMapS2T = new HashMap<>();
        Map<String, String> charMapT2S = new HashMap<>();
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        for (int i = 0; i < sArray.length; i++) {
            char sChar = sArray[i];
            char tChar = tArray[i];

            if (charMapS2T.get(charToString(sChar)) != null && !charMapS2T.get(charToString(sChar)).equals(charToString(tChar))) {
                return false;
            }
            if (charMapT2S.get(charToString(tChar)) != null && !charMapT2S.get(charToString(tChar)).equals(charToString(sChar))) {
                return false;
            }

            if (charMapS2T.get(charToString(sChar)) == null) {
                charMapS2T.put(charToString(sChar), charToString(tChar));
                charMapT2S.put(charToString(tChar), charToString(sChar));
            }
        }
        return true;
    }

    private String charToString(char sChar) {
        return String.valueOf(sChar);
    }

    private boolean valid(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        return true;
    }
}
