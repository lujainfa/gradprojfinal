package com.lujainalfalaileh.bloodbank;



import android.widget.TextView;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Users {
    private String age;
    private String name;
    private String text1,text2,text3,text4,text5,text6;
    public Users(){}


    public Users(String name, String age, String gender, String blood,
                 TextView text1, TextView text2, TextView text3, TextView text4, TextView text5, TextView text6) {

    }

    public String getBlood() {
        return blood;
    }

    public String getText1() {
        return text1;
    }
    public String getText2() {
        return text2;
    }
    public String getText3() {
        return text3;
    }
    public String getText4() {
        return text4;
    }
    public String getText5() {
        return text5;
    }
    public String getText6() {
        return text6;
    }


    private void setText1(String text1) {
        this.text1= text1;
    }
    private void  setText2(String text2) {
        this.text2=text2;
    }
    private void  setText3(String text3) {
        this.text3=text3;
    }
    private void  setText4(String text4) {
        this.text4=text4;
    }
    private void  setText5(String text5) {this.text5= text5;}
    private void  setText6(String text6) {
        this.text6=text6;
    }


    public void setBlood(String blood) {
        this.blood = blood;
    }

    private String blood;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;


    public Users(String name, String age, String gender,String blood, String text1,
                 String text2,String text3,String text4,String text5,String text6) {
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.blood=blood;
        this.text1=text1;
        this.text2=text2;
        this.text3=text3;
        this.text5=text5;
        this.text4=text4;
        this.text6=text6;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }














}
