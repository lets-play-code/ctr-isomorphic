package com.sap.ase;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicChecker {
    public boolean isIsomorphic(String s, String t) {
        if (!valid(s, t)) return false;
        CharacterBinding characterBinding = new CharacterBinding();
        for (int i = 0; i < s.length(); i++) {
            if (!characterBinding.isBound(s.charAt(i), t.charAt(i))) return false;
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

    static class CharacterBinding {
        Map<Character, Character> oneWayMapping = new HashMap<>();
        Map<Character, Character> anotherWayMapping = new HashMap<>();

        private boolean isBound(Character sChar, Character tChar) {
            return oneWayBound(sChar, tChar, oneWayMapping) && oneWayBound(tChar, sChar, anotherWayMapping);
        }

        private boolean oneWayBound(char sChar, char tChar, Map<Character, Character> charMapS2T) {
            if (charMapS2T.get(sChar) != null && charMapS2T.get(sChar) != tChar) {
                return false;
            }
            charMapS2T.put(sChar, tChar);
            return true;
        }
    }
}
