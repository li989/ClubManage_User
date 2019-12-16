package com.example.club.model;

import java.util.Date;

public class Beanschoolnotice {
    private int schoolnotice_ID;
    private String schoolnotice_title;
    private String schoolnotice_content;
    private Date schoolnotice_start_time;
    private String schoolnotice_status;
    private byte[] schoolnotice_picture;
    private int schoolnotice_number;
    public int getSchoolnotice_number() {
        return schoolnotice_number;
    }

    public void setSchoolnotice_number(int schoolnotice_number) {
        this.schoolnotice_number = schoolnotice_number;
    }

    public String getSchoolnotice_title() {
		return schoolnotice_title;
	}

	public void setSchoolnotice_title(String schoolnotice_title) {
		this.schoolnotice_title = schoolnotice_title;
	}

	public String getSchoolnotice_status() {
		return schoolnotice_status;
	}

	public void setSchoolnotice_status(String schoolnotice_status) {
		this.schoolnotice_status = schoolnotice_status;
	}

	public byte[] getSchoolnotice_picture() {
		return schoolnotice_picture;
	}

	public void setSchoolnotice_picture(byte[] schoolnotice_picture) {
		this.schoolnotice_picture = schoolnotice_picture;
	}

	public int getSchoolnotice_ID() {
        return schoolnotice_ID;
    }

    public String getSchoolnotice_content() {
        return schoolnotice_content;
    }

    public Date getSchoolnotice_start_time() {
        return schoolnotice_start_time;
    }

    public void setSchoolnotice_ID(int schoolnotice_ID) {
        this.schoolnotice_ID = schoolnotice_ID;
    }

    public void setSchoolnotice_content(String schoolnotice_content) {
        this.schoolnotice_content = schoolnotice_content;
    }

    public void setSchoolnotice_start_time(Date schoolnotice_start_time) {
        this.schoolnotice_start_time = schoolnotice_start_time;
    }
}
