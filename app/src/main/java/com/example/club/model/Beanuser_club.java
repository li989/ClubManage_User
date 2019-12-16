package com.example.club.model;

import java.util.Date;

public class Beanuser_club {
    private int c_user_ID;
    private int club_ID;
    private String club_status;
    private Date club_time;
    String user_grade;
    public String getUser_grade() {
        return user_grade;
    }

    public void setUser_grade(String user_grade) {
        this.user_grade = user_grade;
    }

    public String getClub_status() {
		return club_status;
	}

	public void setClub_status(String club_status) {
		this.club_status = club_status;
	}

	public Date getClub_time() {
		return club_time;
	}

	public void setClub_time(Date club_time) {
		this.club_time = club_time;
	}

	public int getC_user_ID() {
        return c_user_ID;
    }

    public int getClub_ID() {
        return club_ID;
    }

    public void setC_user_ID(int c_user_ID) {
        this.c_user_ID = c_user_ID;
    }

    public void setClub_ID(int club_ID) {
        this.club_ID = club_ID;
    }
}
