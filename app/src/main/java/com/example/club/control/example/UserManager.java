package com.example.club.control.example;


import com.example.club.model.Beanmycl;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;
import com.example.club.util.BusinessException;
import com.example.club.util.DBUtil;
import com.example.club.util.DbException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class UserManager {
	public String loadselectpro(int ID) throws BaseException {
		String a="";
		Connection conn=null;
		try {
			conn= DBUtil.getConnection();
			String sql="select c_name from user where c_userID=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, ID);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				a=rs.getString(1);;
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
		return a;
	}
	public String createuser(Beanuser user) throws BusinessException {
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select * from user where c_number=? and c_deletetime is null";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user.getC_number());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) return "该用户名已被注册";
			pst.close();
			rs.close();
			sql="select c_userID from user where c_userID=(select max(c_userID) from user )";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			int max=0;
			if(rs.next())
				max=rs.getInt(1);
			//MD5
			char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
			byte[] strTemp = user.getC_pwd().getBytes();
			MessageDigest messageDigest = null;
			try {
				messageDigest = MessageDigest.getInstance("md5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			messageDigest.update(strTemp);
			byte[] md = messageDigest.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			String a=new String(str);
			sql="INSERT INTO user VALUES(?,?,?,NOW(),NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,?,NULL,NULL)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,max+1 );
			pst.setString(2, user.getC_number());
			pst.setString(3,a.toLowerCase());
			pst.setString(4, user.getC_mail());
			pst.execute();
			pst.close();
			return "注册成功";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Beanuser loaduser() throws BusinessException {
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select * from user where c_userID=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Beanuser.currentLoginUser.getC_userID());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				throw new BaseException("�û��Ų� ����");
			}
			Beanuser user =new Beanuser();
			user.setC_userID(rs.getInt(1));
			user.setC_number(rs.getString(2));
			user.setC_pwd(rs.getString(3));
			user.setC_creationtime(rs.getTimestamp(4));
			user.setC_deletetime(rs.getTimestamp(5));
			user.setC_grade(rs.getString(6));
			user.setC_brithday(rs.getTimestamp(7));
			user.setC_sex(rs.getString(8));
			user.setC_name(rs.getString(9));
			user.setC_sno(rs.getString(10));
			user.setC_stugrade(rs.getString(11));
			user.setC_major(rs.getString(12));
			user.setC_mail(rs.getString(13));
			pst.close();
			rs.close();
			return user;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public Beanuser selectuserbyid(int id) throws BusinessException {
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select * from user where c_userID=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next())
			{
				return null;
			}
			Beanuser user =new Beanuser();
			user.setC_userID(rs.getInt(1));
			user.setC_number(rs.getString(2));
			user.setC_pwd(rs.getString(3));
			user.setC_creationtime(rs.getTimestamp(4));
			user.setC_deletetime(rs.getTimestamp(5));
			user.setC_grade(rs.getString(6));
			user.setC_brithday(rs.getTimestamp(7));
			user.setC_sex(rs.getString(8));
			user.setC_name(rs.getString(9));
			user.setC_sno(rs.getString(10));
			user.setC_stugrade(rs.getString(11));
			user.setC_major(rs.getString(12));
			user.setC_mobile(rs.getString(13));
			user.setC_mail(rs.getString(14));
			user.setC_picture(rs.getBytes(15));
			pst.close();
			rs.close();
			return user;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> selectszpm() throws BusinessException {
		Connection conn=null;
		ArrayList<String> names=new ArrayList<String>();
		try{
			conn=DBUtil.getConnection();
			String sql="select c_name from user a,club b where a.c_userID=b.club_proID ORDER BY b.club_grade DESC";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next()) {
				names.add(rs.getString(1));
			}
			pst.close();
			rs.close();
			return names;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	public Beanuser selectuser(String zh) throws BusinessException {
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select * from user where c_number=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,zh);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next())
			{
				throw new BaseException("无此账号");
			}
			Beanuser user =new Beanuser();
			user.setC_userID(rs.getInt(1));
			user.setC_number(rs.getString(2));
			user.setC_pwd(rs.getString(3));
			user.setC_creationtime(rs.getTimestamp(4));
			user.setC_deletetime(rs.getTimestamp(5));
			user.setC_grade(rs.getString(6));
			user.setC_brithday(rs.getTimestamp(7));
			user.setC_sex(rs.getString(8));
			user.setC_name(rs.getString(9));
			user.setC_sno(rs.getString(10));
			user.setC_stugrade(rs.getString(11));
			user.setC_major(rs.getString(12));
			user.setC_mobile(rs.getString(13));
			user.setC_mail(rs.getString(14));
			user.setC_picture(rs.getBytes(15));
			pst.close();
			rs.close();
			return user;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public Beanuser modifyuser(Beanuser user) throws BusinessException {
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="update user set c_pwd = ?,c_grade=?,c_brithday=?,c_sex=?,c_name=?,c_sno=?,c_stugrade=?,c_major=?,c_mail=?,c_mobile=?,c_picture=? where c_userID=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user.getC_pwd());
			pst.setString(2,user.getC_grade());
			pst.setTimestamp(3,user.getC_brithday());
			pst.setString(4,user.getC_sex());
			pst.setString(5,user.getC_name());
			pst.setString(6,user.getC_sno());
			pst.setString(7,user.getC_stugrade());
			pst.setString(8,user.getC_major());
			pst.setString(9,user.getC_mail());
			pst.setString(10,user.getC_mobile());
			pst.setInt(12,user.getC_userID());
			pst.setBytes(11,user.getC_picture());
			pst.execute();
			pst.close();
			return this.selectuser(user.getC_number());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public void deleteuser(int userid) throws BusinessException {
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="update user set c_deletetime = now() where c_userID=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,userid);
			pst.execute();
			pst.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public Beanuser login(String zh,String pwd){
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select c_pwd from user where c_number=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,zh);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BaseException(zh);
			char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
			byte[] strTemp = pwd.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
			messageDigest.update(strTemp);
			byte[] md = messageDigest.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			String a=new String(str);
			a=a.toLowerCase();
			String b=rs.getString(1);
			if(a.equals(b)) {
				rs.close();
				return  (new UserManager()).selectuser(zh);
			}else{
				throw new BaseException(a+"\n"+b);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


    public List<Beanmycl> getmyclub(int id) {
		List<Beanmycl> result=new ArrayList<Beanmycl>();
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select a.*,b.club_name,b.club_picture from user_club a,club b where a.club_id=b.club_id and a.club_status='已通过' and a.c_user_ID="+id;
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next())
			{

				Beanmycl mycl=new Beanmycl();
				mycl.setClub_name(rs.getString(6));
				mycl.setClub_picture(rs.getBytes(7));
				mycl.setJoin_time(rs.getTimestamp(4));
				mycl.setUser_grade(rs.getString(5));
				mycl.setClub_id(rs.getInt(2));
				result.add(mycl);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
    }

	public Beanuser changepwd(Beanuser user,String pwd){
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="update user set c_pwd=? where c_number=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
			byte[] strTemp = pwd.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
			messageDigest.update(strTemp);
			byte[] md = messageDigest.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			String a=new String(str);
			a=a.toLowerCase();
			pst.setString(1,a);
			pst.setString(2,user.getC_number());
			pst.execute();
			user.setC_pwd(a);
			return user;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
