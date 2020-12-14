package com.example.brightacademy.model;

public class User {

    public User(){

    }

    private int id;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private UserRole userRole;



    public User(String username, String fullName, String email,
                String password, UserRole userRole) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    private int img;
//
//    public int getImg() {
//        return img;
//    }
//
//    public void setImg(int img) {
//        this.img = img;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}

