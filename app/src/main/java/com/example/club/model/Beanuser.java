package com.example.club.model;

import com.example.club.control.example.UserManager;

import java.sql.Timestamp;
import java.util.Date;

public class Beanuser {


	public static Beanuser currentLoginUser;
    private int c_userID;
    private String c_number;
    private String c_pwd;
    private Timestamp c_creationtime;
    private Timestamp c_deletetime;
    private String c_grade;
    private Timestamp c_brithday;
    private String c_sex;
    private String c_name;
    private String c_sno;
    private String c_stugrade;
    private String c_major;
	private String c_mobile;
    private String c_mail;
    private byte[] c_picture;

	public byte[] getC_picture() {
		return c_picture;
	}
	public void setC_picture(byte[] c_picture) {
		this.c_picture = c_picture;
	}
	public int getC_userID() {
		return c_userID;
	}
	public void setC_userID(int c_userID) {
		this.c_userID = c_userID;
	}
	public String getC_number() {
		return c_number;
	}
	public void setC_number(String c_number) {
		this.c_number = c_number;
	}
	public String getC_pwd() {
		return c_pwd;
	}
	public void setC_pwd(String c_pwd) {
		this.c_pwd = c_pwd;
	}
	public Timestamp getC_creationtime() {
		return c_creationtime;
	}
	public void setC_creationtime(Timestamp c_creationtime) {
		this.c_creationtime = c_creationtime;
	}
	public Timestamp getC_deletetime() {
		return c_deletetime;
	}
	public void setC_deletetime(Timestamp c_deletetime) {
		this.c_deletetime = c_deletetime;
	}
	public String getC_grade() {
		return c_grade;
	}
	public void setC_grade(String c_grade) {
		this.c_grade = c_grade;
	}
	public Timestamp getC_brithday() {
		return c_brithday;
	}
	public void setC_brithday(Timestamp c_brithday) {
		this.c_brithday = c_brithday;
	}
	public String getC_sex() {
		return c_sex;
	}
	public void setC_sex(String c_sex) {
		this.c_sex = c_sex;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_sno() {
		return c_sno;
	}
	public void setC_sno(String c_sno) {
		this.c_sno = c_sno;
	}
	public String getC_stugrade() {
		return c_stugrade;
	}
	public void setC_stugrade(String c_stugrade) {
		this.c_stugrade = c_stugrade;
	}
	public String getC_major() {
		return c_major;
	}
	public void setC_major(String c_major) {
		this.c_major = c_major;
	}
	public String getC_mail() {
		return c_mail;
	}
	public void setC_mail(String c_mail) {
		this.c_mail = c_mail;
	}
	public String getC_mobile() {
		return c_mobile;
	}
	public void setC_mobile(String c_mobile) {
		this.c_mobile = c_mobile;
	}
}
