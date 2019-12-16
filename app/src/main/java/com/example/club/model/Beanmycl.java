package com.example.club.model;

import java.util.Date;

public class Beanmycl {
    private String club_name;
    private int club_id;
    private Date join_time;
    private byte[] club_picture;
    private String user_grade;

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public Date getJoin_time() {
        return join_time;
    }

    public void setJoin_time(Date join_time) {
        this.join_time = join_time;
    }

    public byte[] getClub_picture() {
        return club_picture;
    }

    public void setClub_picture(byte[] club_picture) {
        this.club_picture = club_picture;
    }

    public String getUser_grade() {
        return user_grade;
    }

    public void setUser_grade(String user_grade) {
        this.user_grade = user_grade;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }
}
