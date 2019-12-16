package com.example.club.model;

import java.util.Date;

public class Beanclubnotice {
    private int clubnotice_ID;
    private int club_ID;
    private String clubnotice_content;
	private Date clubnotice_start_time;
    private String clubnotice_title;
    private byte[] clubnotice_picture;

    public int getClubnotice_number() {
        return clubnotice_number;
    }

    public void setClubnotice_number(int clubnotice_number) {
        this.clubnotice_number = clubnotice_number;
    }

    private int clubnotice_number;
    public String getClubnotice_title() {
		return clubnotice_title;
	}

	public void setClubnotice_title(String clubnotice_title) {
		this.clubnotice_title = clubnotice_title;
	}

	public byte[] getClubnotice_picture() {
		return clubnotice_picture;
	}

	public void setClubnotice_picture(byte[] clubnotice_picture) {
		this.clubnotice_picture = clubnotice_picture;
	}
    public void setClubnotice_ID(int clubnotice_ID) {
        this.clubnotice_ID = clubnotice_ID;
    }

    public void setClub_ID(int club_ID) {
        this.club_ID = club_ID;
    }

    public void setClubnotice_content(String clubnotice_content) {
        this.clubnotice_content = clubnotice_content;
    }

    public void setClubnotice_start_time(Date clubnotice_start_time) {
        this.clubnotice_start_time = clubnotice_start_time;
    }

    public int getClubnotice_ID() {
        return clubnotice_ID;
    }

    public int getClub_ID() {
        return club_ID;
    }

    public String getClubnotice_content() {
        return clubnotice_content;
    }

    public Date getClubnotice_start_time() {
        return clubnotice_start_time;
    }

}
