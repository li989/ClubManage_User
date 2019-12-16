package com.example.club.control.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.club.model.Beanclub_label;
import com.example.club.model.Beanlabel;
import com.example.club.util.BaseException;
import com.example.club.util.BusinessException;
import com.example.club.util.DBUtil;
import com.example.club.util.DbException;

public class Club_LabelManager {
	//�޸ģ��������ű�ǩ
	public void addClub_label(List<Beanlabel> label, int club_ID) throws BaseException {
	    Connection conn=null;
	        try {
	            conn=DBUtil.getConnection();
	            String sql="select * from club_label where club_ID=?";
	            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
	            pst.setInt(1,club_ID);
	            java.sql.ResultSet rs=pst.executeQuery();
	            if(rs.next()) {
	            	pst=conn.prepareStatement("delete from club_label where club_ID=?");
	        		pst.setInt(1, club_ID);
	        		pst.execute();
	        	    pst.close();
	            }
	            for(int i=0;i<label.size();i++) {
	            rs.close();
	            pst.close();
	            sql="INSERT INTO club_label(label_ID,club_ID) values(?,?)";
	            pst=conn.prepareStatement(sql);
	            pst.setInt(1, label.get(i).getID());
	            pst.setInt(2, club_ID);
	            pst.execute();
	            pst.close();}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	//��ʾ���б�ǩ
	public List<Beanclub_label> loadAll() throws BaseException {
		List<Beanclub_label> result=new ArrayList<Beanclub_label>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from club_label";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			Beanclub_label a=new Beanclub_label();
			a.setClub_ID(rs.getInt(2));
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
	//��ʾ����ѡ��ı�ǩ
	public List<Beanclub_label> loadAllclub(int club_ID) throws BaseException {
		List<Beanclub_label> result=new ArrayList<Beanclub_label>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from club_introducein where club_ID=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,club_ID);
            java.sql.ResultSet rs=pst.executeQuery();
            if(!rs.next()) throw new BusinessException("社团不存在");
			sql="select * from club_label where club_ID=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, club_ID);
		    rs=pst.executeQuery();
		while(rs.next()) {
			Beanclub_label a=new Beanclub_label();
			a.setClub_ID(rs.getInt(2));
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
