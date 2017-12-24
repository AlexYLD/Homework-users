package com.company.validators;

public class EmailValidator {
    public static boolean validate(String s){
        if(s==null){
            System.out.println("Field is empty");
            return false;
        }
        if(s.matches("[\\w-_]+@\\w+.\\w+")){
            return true;
        }
        System.out.println("Not a valid email");
        return false;
    }
}
