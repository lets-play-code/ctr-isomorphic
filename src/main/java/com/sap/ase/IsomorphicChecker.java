package com.sap.ase;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicChecker {
    class CharacterBinding {
        Map<Character, Character> charMapS2T = new HashMap<>();
        Map<Character, Character> charMapT2S = new HashMap<>();
    }

    public boolean isIsomorphic(String s, String t) {
        if (!valid(s, t)) return false;
        CharacterBinding characterBinding = new CharacterBinding();

        for (int i = 0; i < s.length(); i++) {
            Character sChar = s.charAt(i);
            Character tChar = t.charAt(i);

            if (isBinded(characterBinding, sChar, tChar)) return false;
        }
        return true;
    }

    private boolean isBinded(CharacterBinding characterBinding, Character sChar, Character tChar) {
        if (characterBinding.charMapS2T.get(sChar) != null && !characterBinding.charMapS2T.get(sChar).equals(tChar)) {
            return true;
        }
        if (characterBinding.charMapT2S.get(tChar) != null && !characterBinding.charMapT2S.get(tChar).equals(sChar)) {
            return true;
        }

        characterBinding.charMapS2T.put(sChar, tChar);
        characterBinding.charMapT2S.put(tChar, sChar);
        return false;
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
