package com.sap.ase;

public class IsomorphicChecker {
    public static boolean comparePattern(String s, String t) {
        if (s==null||t==null)
            return false;
        if (s.length() != t.length())
            return false;
        for(int i=0; i< s.length()-1; i++){
            if (isNotSameAt(s, t, i, 1, i + 1)) return false;
            if (i < s.length() - 2) {
                if (isNotSameAt(s, t, i, 2, i + 2)) return false;
            }
        }
        return true;
    }

    private static boolean isNotSameAt(String s, String t, int i, int i2, int i1) {
        if (s.charAt(i) == s.charAt(i1) && t.charAt(i) != t.charAt(i1))
            return true;
        if (s.charAt(i) != s.charAt(i1) && t.charAt(i) == t.charAt(i1))
            return true;
        return false;
    }
}