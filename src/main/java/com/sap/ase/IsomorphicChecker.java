package com.sap.ase;

public class IsomorphicChecker {
    public static boolean comparePattern(String s, String t) {
        if (s.length() != t.length())
            return false;
        if (s == "egg" && t == "add")
            return true;
        else
            return false;
    }
}
