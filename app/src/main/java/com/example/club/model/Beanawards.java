package com.example.club.model;

import java.util.Date;

public class Beanawards {
    private int awards_ID;
    private int club_ID;
    private String awards_name;
    private byte[] awards_picture;
    private int awards_grade;
    private String awards_status;
    private Date awards_time;
    public Date getAwards_time() {
		return awards_time;
	}

	public void setAwards_time(Date awards_time) {
		this.awards_time = awards_time;
	}

	public String getAwards_status() {
		return awards_status;
	}

	public void setAwards_status(String awards_status) {
		this.awards_status = awards_status;
	}

	public int getClub_ID() {
        return club_ID;
    }

    public void setAwards_ID(int awards_ID) {
        this.awards_ID = awards_ID;
    }

    public void setClub_ID(int club_ID) {
        this.club_ID = club_ID;
    }

    public void setAwards_name(String awards_name) {
        this.awards_name = awards_name;
    }

    public void setAwards_picture(byte[] awards_picture) {
        this.awards_picture = awards_picture;
    }

    public void setAwards_grade(int awards_grade) {
        this.awards_grade = awards_grade;
    }

    public int getAwards_ID() {
        return awards_ID;
    }

    public String getAwards_name() {
        return awards_name;
    }

    public byte[] getAwards_picture() {
        return awards_picture;
    }

    public int getAwards_grade() {
        return awards_grade;
    }
}
