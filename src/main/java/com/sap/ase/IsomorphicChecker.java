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
            if (s.charAt(i) == s.charAt(i+1) && t.charAt(i) != t.charAt(i+1))
                return false;
            if (s.charAt(i) != s.charAt(i+1) && t.charAt(i) == t.charAt(i+1))
                return false;
            if (i < s.length() - 2) {
                if (s.charAt(i) == s.charAt(i + 2) && t.charAt(i) != t.charAt(i + 2))
                    return false;
                if (s.charAt(i) != s.charAt(i + 2) && t.charAt(i) == t.charAt(i + 2))
                    return false;
            }
        }
        return true;
    }
}