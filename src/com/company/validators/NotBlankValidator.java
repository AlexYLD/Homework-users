package com.company.validators;

public class NotBlankValidator {
    public static boolean validate(String s){
        if(s==null){
            System.out.println("Field is NULL");
            return false;
        }
        if(s.equals("")){
            System.out.println("Input is empty");
            return false;
        }
        if(s.matches("\\s+")){
            System.out.println("Input contains only whitespaces");
            return false;
        }
        return true;
    }
}
