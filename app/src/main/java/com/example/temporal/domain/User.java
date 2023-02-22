package com.example.temporal.domain;

public final class User {

    public static String UID = "uid";
    public static String NAME = "name";
    public static String PHONE = "phone";
    public static String EMAIL = "email";
    public static String LASTNAME = "lastName";
    public static String PASSWORD = "password";

    public static String COLLECTION = "users";

    public static Exception EXCEPTION = new Exception("No se ha podido cargar los datos, porfavor vuelva a iniciar sesión");

    private String uid;
    private String name;
    private String phone;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
