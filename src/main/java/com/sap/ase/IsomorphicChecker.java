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

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            if (charMapS2T.get(sChar) != null && !charMapS2T.get(sChar).equals(tChar)) {
                return false;
            }
            if (charMapT2S.get(tChar) != null && !charMapT2S.get(tChar).equals(sChar)) {
                return false;
            }

            charMapS2T.put(sChar, tChar);
            charMapT2S.put(tChar, sChar);
        }
        return true;
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
