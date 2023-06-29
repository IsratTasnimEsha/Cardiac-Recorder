package com.example.gitproject;

public class UserClass {
    String Phone, Systolic, Diastolic, Heart_Rate, Comment, Time;

<<<<<<< HEAD
=======
    public UserClass() {

    }

    public UserClass(String phone, String systolic, String diastolic, String heart_Rate, String comment, String time) {
        Phone = phone;
        Systolic = systolic;
        Diastolic = diastolic;
        Heart_Rate = heart_Rate;
        Comment = comment;
        Time = time;
    }

>>>>>>> esha
    public String getPhone() {
        return Phone;
    }

    public String getSystolic() {
        return Systolic;
    }

    public String getDiastolic() {
        return Diastolic;
    }

    public String getHeart_Rate() {
        return Heart_Rate;
    }

    public String getComment() {
        return Comment;
    }

    public String getTime() {
        return Time;
    }
}