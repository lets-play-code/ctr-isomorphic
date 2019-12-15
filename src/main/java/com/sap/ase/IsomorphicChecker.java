package com.sap.ase;

public class IsomorphicChecker {
    public static boolean comparePattern(String s, String t) {
        if (s==null||t==null)
            return false;
        if (s.length() != t.length())
            return false;
        for(int i=0; i< s.length()-1; i++){
            for (int j = i+1; j < s.length(); j++) {
                if (isNotSameAt(s, t, i, j)) return false;
            }
        }
        return true;
    }

    private static boolean isNotSameAt(String s, String t, int i, int i1) {
        return s.charAt(i) == s.charAt(i1) && t.charAt(i) != t.charAt(i1) || s.charAt(i) != s.charAt(i1) && t.charAt(i) == t.charAt(i1);
    }
}