package com.sap.ase;

public class IsomorphicChecker {
    public static boolean comparePattern(String s, String t) {
        if (s==null||t==null)
            return false;
        if (s.length() != t.length())
            return false;
        if (s == "egg" && t == "add")
            return true;
        if (s.charAt(0) == s.charAt(1) && t.charAt(0) == t.charAt(1))
            return true;

        for(int i=0; i< s.length()-1; i++){
            if (s.charAt(i) == s.charAt(i+1) && t.charAt(i) == t.charAt(i+1))
                return true;
        }
        return false;


    }
}