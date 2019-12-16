package com.example.club.control.example;

import com.example.club.model.Beanapplication;
import com.example.club.util.BusinessException;
import com.example.club.util.DBUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;



public class ApplicationManager {
	public void createapplication(Beanapplication application) throws BusinessException {
		Connection conn=null;
		try{
			conn= DBUtil.getConnection();
			String sql="select application_ID from application where application_ID=(select max(application_ID) from application)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			int max=0;
			if(rs.next())
				max=rs.getInt(1);
			//MD5
			pst.close();
			rs.close();
			sql="INSERT INTO user VALUES(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, max+1);
			pst.setInt(2, application.getApplication_wID());
			pst.setString(3, application.getApplication_type());
			pst.setString(4, application.getApplication_name());
			pst.setString(5, application.getApplication_remark());
			pst.setString(6, "������");
			pst.execute();
			pst.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	//��������
	
	public List<Beanapplication> loadallapplication() throws BusinessException {
		List<Beanapplication> result=new ArrayList<Beanapplication>();
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select * from application where application_status not like '��ͨ��'  or application_status not like 'δͨ��'  order by application_time";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();			
			while(rs.next()) {
				Beanapplication apy=new Beanapplication();
				apy.setApplication_ID(rs.getInt(1));
				apy.setApplication_wID(rs.getInt(2));
				apy.setApplication_type(rs.getString(3));
				apy.setApplication_name(rs.getString(4));
				apy.setApplication_remark(rs.getString(5));
				apy.setApplication_status(rs.getString(6));
				apy.setApplication_status(rs.getString(7));
				result.add(apy);
			}
			pst.close();
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}
	public void modifyapplication(int id,String choose) throws BusinessException {
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="update application set application_status = ? where application_ID= ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, choose);
			pst.setInt(2, id);
			pst.execute();
			pst.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
