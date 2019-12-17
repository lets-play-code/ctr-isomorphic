package com.sap.ase;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicChecker {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }

        Map<String, String> charMapS2T = new HashMap<>();
        Map<String, String> charMapT2S = new HashMap<>();
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        for (int i = 0; i < sArray.length; i++) {
            char sChar = sArray[i];
            char tChar = tArray[i];

            if (charMapS2T.get(String.valueOf(sChar)) != null && !charMapS2T.get(String.valueOf(sChar)).equals(String.valueOf(tChar))) {
                return false;
            }
            if (charMapT2S.get(String.valueOf(tChar)) != null && !charMapT2S.get(String.valueOf(tChar)).equals(String.valueOf(sChar))) {
                return false;
            }

            if (charMapS2T.get(String.valueOf(sChar)) == null) {
                charMapS2T.put(String.valueOf(sChar), String.valueOf(tChar));
                charMapT2S.put(String.valueOf(tChar), String.valueOf(sChar));
            }
        }
        return true;
    }
}
