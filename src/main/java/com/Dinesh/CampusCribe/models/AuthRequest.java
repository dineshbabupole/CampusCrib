package com.Dinesh.CampusCribe.models;

public class AuthRequest {


    private String userName;
    private String Password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return Password;
    }
}
