package com.example.club.model;

import java.util.Date;

public class Beanclub_person {
    private String c_name;
    private byte[] c_picture;
    private int c_userID;
    private String user_grade;
    private Date user_time;
    private int club_ID;
    public int getClub_ID() {
        return club_ID;
    }

    public void setClub_ID(int club_ID) {
        this.club_ID = club_ID;
    }

    public Date getUser_time() {
        return user_time;
    }

    public void setUser_time(Date user_time) {
        this.user_time = user_time;
    }

    public int getC_userID() {
        return c_userID;
    }

    public void setC_userID(int c_userID) {
        this.c_userID = c_userID;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public byte[] getC_picture() {
        return c_picture;
    }

    public void setC_picture(byte[] c_picture) {
        this.c_picture = c_picture;
    }

    public String getUser_grade() {
        return user_grade;
    }

    public void setUser_grade(String user_grade) {
        this.user_grade = user_grade;
    }
}
