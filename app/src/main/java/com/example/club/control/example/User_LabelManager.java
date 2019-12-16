package com.example.club.control.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.club.model.Beanclub_label;
import com.example.club.model.Beanlabel;
import com.example.club.model.Beanuser;
import com.example.club.model.Beanuser_label;
import com.example.club.util.BaseException;
import com.example.club.util.BusinessException;
import com.example.club.util.DBUtil;
import com.example.club.util.DbException;

public class User_LabelManager {
	public void addUser_label(List<Beanlabel> label, Beanuser user) throws BaseException {
	    Connection conn=null;
	        try {
	            conn=DBUtil.getConnection();
	            String sql="select * from user_label where c_user_ID=?";
	            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
	            pst.setInt(1,user.getC_userID());
	            java.sql.ResultSet rs=pst.executeQuery();
	            if(rs.next()) {
	            	pst=conn.prepareStatement("delete from user_label where c_user_ID=?");
	        		pst.setInt(1, user.getC_userID());
	        		pst.execute();
	        	    pst.close();
	            }
	            for(int i=0;i<label.size();i++) {
	            rs.close();
	            pst.close();
	            sql="INSERT INTO user_label(label_ID,c_user_ID) values(?,?)";
	            pst=conn.prepareStatement(sql);
	            pst.setInt(1, label.get(i).getID());
	            pst.setInt(2, user.getC_userID());
	            pst.execute();
	            pst.close();}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	//��ʾ���б�ǩ
	public List<Beanuser_label> loadAll() throws BaseException {
		List<Beanuser_label> result=new ArrayList<Beanuser_label>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from user_label";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			Beanuser_label a=new Beanuser_label();
			a.setC_user_ID(rs.getInt(2));
			a.setLabel_ID(rs.getInt(1));
			result.add(a);
		}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
	//��ʾ�û�ѡ��ı�ǩ
	public List<Beanuser_label> loadAlluser(int user_ID) throws BaseException {
		List<Beanuser_label> result=new ArrayList<Beanuser_label>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from user where c_user_ID=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,user_ID);
            java.sql.ResultSet rs=pst.executeQuery();
            if(!rs.next()) throw new BusinessException("用户不存在");
			sql="select * from user_label where c_user_ID=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, user_ID);
		    rs=pst.executeQuery();
		while(rs.next()) {
			Beanuser_label a=new Beanuser_label();
			a.setC_user_ID(rs.getInt(2));
			a.setLabel_ID(rs.getInt(1));
			result.add(a);
		}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
}
