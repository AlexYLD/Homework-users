package com.company.controller;

import com.company.annotations.Email;
import com.company.annotations.Length;
import com.company.annotations.NotBlank;
import com.company.entities.User;
import com.company.annotations.HowToPrint;
import com.company.validators.EmailValidator;
import com.company.validators.LengthValidator;
import com.company.validators.NotBlankValidator;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainWriter {

    private static final String FILENAME = "C:/Users/ayarygix/workspaceJava/Serialization/resources/ForTests.txt";

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        String forParse;
        String[] afterParse = new String[User.class.getDeclaredFields().length];
        int fieldCount = 0;
        Field[] declaredFields = User.class.getDeclaredFields();
        boolean breaker = false;
        boolean notBlank;
        boolean validLength;
        boolean validEmail;

        try (InputStreamReader in = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(in)) {
            while (!breaker) {
                for (Field field : declaredFields) {
                    if (field.getName().equals("id")) continue;
                    do {
                        System.out.println("Insert " + getFieldName(field) + " or -1 to exit");
                        forParse = br.readLine();
                        if (forParse.equals("-1")) {
                            breaker = true;
                            break;
                        }
                        validLength = isLengthValid(field, forParse);
                        notBlank = isNotBlank(field, forParse);
                        validEmail = isValidEmail(field, forParse);
                    } while (!notBlank || !validLength || !validEmail);
                    if (breaker) break;
                    afterParse[fieldCount++] = forParse;
                }
                if (breaker) break;
                User user = User.class.newInstance();

                fieldCount = 0;
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    if (field.getName().equals("id")) {
                        field.set(user, users.size() + 1);
                    } else {
                        field.set(user, afterParse[fieldCount++]);
                    }
                }
                fieldCount = 0;
                if (!users.contains(user)) {
                    users.add(user);
                    System.out.println("User was added");
                    srialize(users);
                } else {
                    System.out.println("User already exists");
                }

            }
        } catch (IOException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }


    }

    private static void srialize(Object users) {
        try {
            FileOutputStream fileOut = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getFieldName(Field field) {
        boolean annotationPresent = field.isAnnotationPresent(HowToPrint.class);
        if (annotationPresent) {
            return field.getAnnotation(HowToPrint.class).printValue();
        }
        return field.getName();
    }

    private static boolean isLengthValid(Field field, String s) {
        boolean annotationPresent = field.isAnnotationPresent(Length.class);
        if (annotationPresent) {
            Length length = field.getAnnotation(Length.class);
            return LengthValidator.validate(s, length.minValue(), length.maxValue());
        }
        return true;
    }

    private static boolean isNotBlank(Field field, String s) {
        boolean annotationPresent = field.isAnnotationPresent(NotBlank.class);
        return !annotationPresent || NotBlankValidator.validate(s);
    }

    private static boolean isValidEmail(Field field, String s) {
        boolean annotationPresent = field.isAnnotationPresent(Email.class);
        return !annotationPresent || EmailValidator.validate(s);
    }
}
