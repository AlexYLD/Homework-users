package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class MainReader {
    private static final String FILENAME = "C:/Users/ayarygix/workspaceJava/Serialization/resources/ForTests.txt";

    public static void main(String[] args) {

        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String forParse;
        String[] afterParse;
        try {
            while (true) {
                System.out.println("Insert Username Password\"-1\" to end");
                forParse = br.readLine();
                if (forParse.equals("-1")) break;
                afterParse = forParse.split(" ");
                ArrayList<User> users = deserialize();
                if (afterParse.length < 2) {
                    System.out.println("Wrong input");
                } else if (!users.contains(new User(0, afterParse[0], null, null, null, null))) {
                    System.out.println("Wrong username");
                } else {
                    User user = users.get(users.indexOf(new User(0, afterParse[0], null, null, null, null)));
                    if (user.getPassword().equals(afterParse[1])) {
                        System.out.println(user);
                    } else {
                        System.out.println("Wrong password");
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static ArrayList<User> deserialize() {
        ArrayList<User> res = null;
        try {
            FileInputStream fileIn = new FileInputStream(FILENAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            res = (ArrayList<User>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
