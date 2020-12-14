package com.example.brightacademy.model;

public class User {

    public User(){

    }

    private int id;
    private String fullName;
    private String email;
    private String password;
    private UserRole userRole;



    public User( String fullName, String email,
                String password, String userRole) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        UserRole defaultUserRole = UserRole.STUDENT;
        if (userRole.equals("Admin")){
            defaultUserRole = UserRole.ADMIN;
        }
        this.userRole = defaultUserRole;
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

