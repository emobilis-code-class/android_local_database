package com.mit.sqlitelocaldb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String firstname;
    private String secondname;
    private String email;
    private String phone;
    private int age;

    public User(int id, String firstname, String secondname, String email, String phone, int age) {
        this.id = id;
        this.firstname = firstname;
        this.secondname = secondname;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    //get settters
    //alt + ins


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
