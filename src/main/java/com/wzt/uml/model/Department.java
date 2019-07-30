package com.wzt.uml.model;

public class Department {
    private int departID;
    private String name;
    private String phone;
    private String email;
    private String manager;

    public int getDepartID() {
        return departID;
    }

    public void setDepartID(int departID) {
        this.departID = departID;
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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
