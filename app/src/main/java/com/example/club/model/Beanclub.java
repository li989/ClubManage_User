package com.example.club.model;

import java.util.Date;

public class Beanclub {
	 private int club_ID;
	    private String club_name;
	    private int club_amount;
	    private String club_remark;
	    private int club_proID;
	    private int club_grade;
	    private byte[] club_picture;
	    private String club_status;
	    private Date club_createtime;
	    private Date club_deletetime;
		private String club_type;
		public String getClub_type() {
			return club_type;
		}

		public void setClub_type(String club_type) {
			this.club_type = club_type;
		}
		public Date getClub_createtime() {
			return club_createtime;
		}
		public void setClub_createtime(Date club_createtime) {
			this.club_createtime = club_createtime;
		}
		public Date getClub_deletetime() {
			return club_deletetime;
		}
		public void setClub_deletetime(Date club_deletetime) {
			this.club_deletetime = club_deletetime;
		}
		public int getClub_ID() {
			return club_ID;
		}
		public void setClub_ID(int club_ID) {
			this.club_ID = club_ID;
		}
		public String getClub_name() {
			return club_name;
		}
		public void setClub_name(String club_name) {
			this.club_name = club_name;
		}
		public int getClub_amount() {
			return club_amount;
		}
		public void setClub_amount(int club_amount) {
			this.club_amount = club_amount;
		}
		public String getClub_remark() {
			return club_remark;
		}
		public void setClub_remark(String club_remark) {
			this.club_remark = club_remark;
		}
		public int getClub_proID() {
			return club_proID;
		}
		public void setClub_proID(int club_proID) {
			this.club_proID = club_proID;
		}
		public int getClub_grade() {
			return club_grade;
		}
		public void setClub_grade(int club_grade) {
			this.club_grade = club_grade;
		}
		public byte[] getClub_picture() {
			return club_picture;
		}
		public void setClub_picture(byte[] club_picture) {
			this.club_picture = club_picture;
		}
		public String getClub_status() {
			return club_status;
		}
		public void setClub_status(String club_status) {
			this.club_status = club_status;
		}
}
