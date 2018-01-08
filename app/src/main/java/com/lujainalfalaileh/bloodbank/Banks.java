package com.lujainalfalaileh.bloodbank;


import com.google.firebase.database.IgnoreExtraProperties;





@IgnoreExtraProperties

public class Banks {
    private String name;
    private String phone;
    private String status;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Banks() {

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




    public Banks(String name, String phone,String status) {
        this.name=name;
        this.phone=phone;
        this.status=status;


    }



}
