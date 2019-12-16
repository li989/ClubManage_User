package com.example.club.model;

import java.util.Date;

public class Beansuper_user {
    private int s_userID;
    private String s_number;
    private String s_password;
    private Date s_creation_time;
    private  Date s_delete_time;
    private byte[] s_picture;
    public byte[] getS_picture() {
		return s_picture;
	}

	public void setS_picture(byte[] s_picture) {
		this.s_picture = s_picture;
	}

	public int getS_userID() {
        return s_userID;
    }

    public String getS_number() {
        return s_number;
    }

    public String getS_password() {
        return s_password;
    }

    public Date getS_creation_time() {
        return s_creation_time;
    }


    public void setS_userID(int s_userID) {
        this.s_userID = s_userID;
    }

    public void setS_number(String s_number) {
        this.s_number = s_number;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public void setS_creation_time(Date s_creation_time) {
        this.s_creation_time = s_creation_time;
    }

	public Date getS_delete_time() {
		return s_delete_time;
	}

	public void setS_delete_time(Date s_delete_time) {
		this.s_delete_time = s_delete_time;
	}
}
