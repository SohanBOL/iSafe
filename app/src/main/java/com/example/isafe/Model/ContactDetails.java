package com.example.isafe.Model;

public class ContactDetails {
    private String name,mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public ContactDetails(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }
}
