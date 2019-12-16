package com.example.club.model;

import java.util.Date;

public class Beanapplication {
    private int application_ID;
    private int application_wID;
    private String application_type;
    private String application_name;
    private String application_remark;
    private String application_status;
    private Date application_time;
	public String getApplication_status() {
		return application_status;
	}
	public void setApplication_status(String application_status) {
		this.application_status = application_status;
	}
	public Date getApplication_time() {
		return application_time;
	}
	public void setApplication_time(Date application_time) {
		this.application_time = application_time;
	}
	public int getApplication_ID() {
		return application_ID;
	}
	public void setApplication_ID(int application_ID) {
		this.application_ID = application_ID;
	}
	public int getApplication_wID() {
		return application_wID;
	}
	public void setApplication_wID(int application_wID) {
		this.application_wID = application_wID;
	}
	public String getApplication_type() {
		return application_type;
	}
	public void setApplication_type(String application_type) {
		this.application_type = application_type;
	}
	public String getApplication_name() {
		return application_name;
	}
	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	public String getApplication_remark() {
		return application_remark;
	}
	public void setApplication_remark(String application_remark) {
		this.application_remark = application_remark;
	}
}
