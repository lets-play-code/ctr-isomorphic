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
            return isOneWayMapping(sChar, tChar, oneWayMapping) && isOneWayMapping(tChar, sChar, anotherWayMapping);
        }

        private boolean isOneWayMapping(char from, char to, Map<Character, Character> charMapping) {
            if (charMapping.containsKey(from) && charMapping.get(from) != to) {
                return false;
            }
            charMapping.put(from, to);
            return true;
        }
    }
}
