package com.lujainalfalaileh.bloodbank;

public class Date {

    String day,month,year,bank;

    Date(){}


    Date(String day,String month,String year,String bank){
        this.day=day;
        this.month=month;
        this.year=year;
        this.bank=bank;
    }
    public  String getBank(){
        return bank;
    }


    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public void setBank(String bank){this.bank=bank;}
    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
