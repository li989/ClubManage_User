package com.example.club.model;
import java.util.Date;

public class Beanactivity {
    private int activity_ID;
    private int club_ID;
    private int place_ID;
    private String activity_name;
    private Date activity_start_time;
    private Date activity_finish_time;
	private int activity_grade;
	private String activity_status;
	private byte[] activity_picture;
    private String activity_centent;
    public String getActivity_centent() {
        return activity_centent;
    }

    public void setActivity_centent(String activity_centent) {
        this.activity_centent = activity_centent;
    }

    public byte[] getActivity_picture() {
		return activity_picture;
	}

	public void setActivity_picture(byte[] activity_picture) {
		this.activity_picture = activity_picture;
	}

	public int getPlace_ID() {
		return place_ID;
	}

	public void setPlace_ID(int place_ID) {
		this.place_ID = place_ID;
	}

    public int getActivity_grade() {
		return activity_grade;
	}

	public void setActivity_grade(int activity_grade) {
		this.activity_grade = activity_grade;
	}


    public void setActivity_ID(int activity_ID) {
        this.activity_ID = activity_ID;
    }

    public void setClub_ID(int club_ID) {
        this.club_ID = club_ID;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public void setActivity_start_time(Date activity_start_time) {
        this.activity_start_time = activity_start_time;
    }

    public void setActivity_finish_time(Date activity_finish_time) {
        this.activity_finish_time = activity_finish_time;
    }

    public void setActivity_status(String activity_status) {
        this.activity_status = activity_status;
    }

    public int getActivity_ID() {
        return activity_ID;
    }

    public int getClub_ID() {
        return club_ID;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public Date getActivity_start_time() {
        return activity_start_time;
    }

    public Date getActivity_finish_time() {
        return activity_finish_time;
    }

    public String getActivity_status() {
        return activity_status;
    }
}
