package com.wzt.uml.model;

public class Member {
    private int userID;
    private String name;
    private String phone;
    private String email;
    private String dapartName;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDapartName() {
        return dapartName;
    }

    public void setDapartName(String dapartName) {
        this.dapartName = dapartName;
    }
}
