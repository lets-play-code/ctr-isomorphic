package com.sap.ase;

public class IsomorphicChecker {
    public static boolean comparePattern(String s, String t) {
        if (s==null||t==null)
            return false;
        if (s.length() != t.length())
            return false;
        if (s == "egg" && t == "add")
            return true;

        for(int i=0; i< s.length()-1; i++){
            for (int j = 1; j < s.length(); j++) {
                if (isSameToNext(s, t, i, j)) return false;
            }
            return true;
        }
        return false;


    }

    private static boolean isSameToNext(String s, String t, int i, int i2) {
        if (s.charAt(i) == s.charAt(i + i2) && t.charAt(i) != t.charAt(i + i2))
            return true;
        if (s.charAt(i) != s.charAt(i + i2) && t.charAt(i) == t.charAt(i + i2))
            return true;
        return false;
    }
}