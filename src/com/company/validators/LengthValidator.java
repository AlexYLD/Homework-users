package com.company.validators;

public class LengthValidator {
    public static boolean validate(String s, int min, int max) {
        if (s == null && min == 0) {
            return true;
        }
        if (s == null) {
            System.out.println("Value is NULL");
            return false;
        }
        if (s.length() >= min && s.length() <= max) {
            return true;
        }
        System.out.println("Length is not valid");
        return false;
    }
}


