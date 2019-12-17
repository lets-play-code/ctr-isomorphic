package com.sap.ase;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicChecker {
    public boolean isIsomorphic(String s, String t) {
        if (!valid(s, t)) return false;

        Map<Character, Character> charMapS2T = new HashMap<>();
        Map<Character, Character> charMapT2S = new HashMap<>();
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

    private Character charToString(char sChar) {
        return sChar;
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
