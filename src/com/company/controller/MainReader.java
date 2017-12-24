package com.company.controller;

import com.company.entities.User;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainReader {
    private static final String FILENAME = "C:/Users/ayarygix/workspaceJava/Serialization/resources/ForTests.txt";

    public static void main(String[] args) {


        String forParse;
        String[] afterParse;
        try (InputStreamReader in = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(in)) {
            while (true) {
                System.out.println("Insert Username Password or \"-1\" to end");
                forParse = br.readLine();
                if (forParse.equals("-1")) break;
                afterParse = forParse.split(" ");

                if (afterParse.length < 2) {
                    System.out.println("Wrong input");
                    continue;
                }
                ArrayList<User> users = deserialize();

                User user = User.class.newInstance();
                Field username = User.class.getDeclaredField("username");
                username.setAccessible(true);
                username.set(user, afterParse[0]);

                if (!users.contains(user)) {
                    System.out.println("Wrong username");
                    continue;
                }
                user = users.get(users.indexOf(user));
                if (!user.getPassword().equals(afterParse[1])) {
                    System.out.println("Wrong password");
                    continue;
                }
                System.out.println(user);

            }
        } catch (IOException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }


    }

    private static ArrayList<User> deserialize() {
        ArrayList<User> res = null;
        try (FileInputStream fileIn = new FileInputStream(FILENAME);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            res = (ArrayList<User>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
