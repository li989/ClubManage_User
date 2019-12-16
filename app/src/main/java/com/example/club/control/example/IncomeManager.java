package com.example.club.control.example;

import com.example.club.model.Beanincome;
import com.example.club.util.BusinessException;
import com.example.club.util.DBUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class IncomeManager {
	public List<Beanincome> loadmouthincome(int club_ID) throws BusinessException {
		List<Beanincome> result=new ArrayList<Beanincome>();
		Connection conn=null;
		try{
			conn= DBUtil.getConnection();
			String sql="select * from income where MONTH(income_time) = MONTH(NOW()) and club_ID=?  order by income_time DESC";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,club_ID);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beanincome ic=new Beanincome();
				ic.setIncome_ID(rs.getInt(1));
				ic.setIncome_amount(rs.getInt(2));
				ic.setIncome_detail(rs.getString(3));
				ic.setIncome_time(rs.getTimestamp(4));
				ic.setClub_ID(rs.getInt(5));
				result.add(ic);
			}
			pst.close();
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}
	public List<Beanincome> loadallincome(int club_ID) throws BusinessException {
		List<Beanincome> result=new ArrayList<Beanincome>();
		Connection conn=null;
		try{
			conn= DBUtil.getConnection();
			String sql="select * from income where club_ID=? order by income_time DESC";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,club_ID);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beanincome ic=new Beanincome();
				ic.setIncome_ID(rs.getInt(1));
				ic.setIncome_amount(rs.getDouble(2));
				ic.setIncome_detail(rs.getString(3));
				ic.setIncome_time(rs.getTimestamp(4));
				ic.setClub_ID(rs.getInt(5));
				result.add(ic);
			}
			pst.close();
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}
	public void addincome(int club_ID,double amount,String detail) throws BusinessException {
		Connection conn=null;
		try{
			conn= DBUtil.getConnection();
			String sql="select Max(income_ID) from income";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			int max=0;
			if(rs.next())
				max=rs.getInt(1);
			//MD5
			pst.close();
			rs.close();
			sql="insert into income values(?,?,?,now(),?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, max+1);
			pst.setDouble(2, amount);
			pst.setString(3, detail);
			pst.setInt(4, club_ID);
			pst.execute();			
			pst.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	public void deleteincome(int id) throws BusinessException {
		Connection conn=null;
		try{
			conn= DBUtil.getConnection();
			String sql="delete from income where income_ID=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.execute();
			pst.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	public double loadgetincome(int club_ID) throws BusinessException {
		double income=0;
		Connection conn=null;
		try{
			conn= DBUtil.getConnection();
			String sql="select SUM(income_amount) from income where MONTH(income_time) = MONTH(NOW()) and club_ID=? and income_amount>0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,club_ID);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				income=rs.getDouble(1);
			}
			pst.close();
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return income;

	}
	public double loadputincome(int club_ID) throws BusinessException {
		double income=0;
		Connection conn=null;
		try{
			conn= DBUtil.getConnection();
			String sql="select SUM(income_amount) from income where MONTH(income_time) = MONTH(NOW()) and club_ID=? and income_amount<0";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,club_ID);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				income=rs.getDouble(1);
			}
			pst.close();
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return income;

	}
}
