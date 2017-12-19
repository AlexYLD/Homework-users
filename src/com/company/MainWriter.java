package com.company;

import java.io.*;
import java.util.ArrayList;

public class MainWriter {

    private static final String FILENAME = "C:/Users/ayarygix/workspaceJava/Serialization/resources/ForTests.txt";

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String forParse;
        String[] afterParse;
        try {
            while (true) {
                System.out.println("Insert Username Password FirstName LastName Email or \"-1\" to end");
                forParse = br.readLine();
                if (forParse.equals("-1")) break;
                afterParse = forParse.split(" ");
                if (afterParse.length < 5) {
                    System.out.println("Wrong input");
                } else {
                    User temp = new User(users.size() + 1, afterParse[0], afterParse[1],
                            afterParse[2], afterParse[3], afterParse[4]);
                    if (!users.contains(temp)) {
                        users.add(temp);
                        srialize(users);
                    } else {
                        System.out.println("User already exists");
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


}
