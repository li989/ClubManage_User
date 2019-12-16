package com.example.club.model;

import java.util.Date;

public class Beanincome {
    private int income_ID;
    private double income_amount;
    private String income_detail;
    private Date income_time;
    private int club_ID;

    public int getClub_ID() {
        return club_ID;
    }

    public void setClub_ID(int club_ID) {
        this.club_ID = club_ID;
    }

    public int getIncome_ID() {
        return income_ID;
    }

    public double getIncome_amount() {
        return income_amount;
    }

    public String getIncome_detail() {
        return income_detail;
    }

    public void setIncome_ID(int income_ID) {
        this.income_ID = income_ID;
    }

    public void setIncome_amount(double income_amount) {
        this.income_amount = income_amount;
    }

    public void setIncome_detail(String income_detail) {
        this.income_detail = income_detail;
    }

	public Date getIncome_time() {
		return income_time;
	}

	public void setIncome_time(Date income_time) {
		this.income_time = income_time;
	}
}
