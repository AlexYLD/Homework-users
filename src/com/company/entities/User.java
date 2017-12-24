package com.company.entities;

import com.company.annotations.Email;
import com.company.annotations.HowToPrint;
import com.company.annotations.Length;
import com.company.annotations.NotBlank;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    @NotBlank
    @HowToPrint(printValue = "User name")
    @Length(minValue = 1, maxValue = 15)
    private String username;

    @NotBlank
    @HowToPrint(printValue = "Password")
    @Length(minValue = 1, maxValue = 15)
    private String password;

    @NotBlank
    @HowToPrint(printValue = "First name")
    @Length(minValue = 1, maxValue = 15)
    private String fname;

    @NotBlank
    @HowToPrint(printValue = "Last name")
    @Length(minValue = 1, maxValue = 15)
    private String lname;

    @NotBlank
    @HowToPrint(printValue = "Email")
    @Email
    private String email;

    public User() {

    }

    public User(int id, String username, String password, String fname, String lname, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    public String getPassword() {
        return password;
    }
}
