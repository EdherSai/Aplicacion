package com.example.myslash.dao;

import java.io.Serializable;

public class Usuarios implements Serializable {

    private int ID;
    private String name;
    private String firstName;
    private String lastName;
    private String userName;
    private String mail;
    private String Age;
    private String Number;
    private String Gender;
    private String Type;
    private String Password;

    public Usuarios()
    {
    }

    public Usuarios(int ID, String name, String firstName, String lastName, String userName, String mail, String Age, String Number, String Gender, String Type, String Password){
        this.ID = ID;
        this.name =  name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.mail = mail;
        this.Age = Age;
        this.Number = Number;
        this.Gender = Gender;
        this.Type = Type;
        this.Password = Password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
