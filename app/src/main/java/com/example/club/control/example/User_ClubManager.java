package com.example.club.control.example;

import android.util.Log;

import com.example.club.model.Beanclub;
import com.example.club.model.Beanclub_person;
import com.example.club.model.Beanuser;
import com.example.club.model.Beanuser_club;
import com.example.club.util.BaseException;
import com.example.club.util.BusinessException;
import com.example.club.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class User_ClubManager {
	public List<Beanclub> loadallclub() throws BusinessException {
		List<Beanclub> result=new ArrayList<Beanclub>();
		Connection conn=null;
		try{
			conn= DBUtil.getConnection();
			String sql="select club_ID from user_club where C_user_ID = ? and club_status ='��ͨ��'";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Beanuser.currentLoginUser.getC_userID());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beanclub club=new Beanclub();
				String sql1="select * from club where club_ID = ?";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(1));
				java.sql.ResultSet rs1=pst1.executeQuery();
				if(rs1.next()) {
					club.setClub_ID(rs1.getInt(1));
					club.setClub_name(rs1.getString(2));
					club.setClub_amount(rs1.getInt(3));
					club.setClub_remark(rs1.getString(4));
					club.setClub_proID(rs1.getInt(5));
					club.setClub_grade(rs1.getInt(6));
					club.setClub_picture(rs1.getBytes(7));
					club.setClub_status(rs1.getNString(8));
					result.add(club);
				}
			}
			pst.close();
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}
	public List<Beanclub> loadallspclub() throws BusinessException {
		List<Beanclub> result=new ArrayList<Beanclub>();
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select club_ID from user_club where C_user_ID = ? and club_status ='已通过'";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Beanuser.currentLoginUser.getC_userID());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beanclub club=new Beanclub();
				String sql1="select * from club where club_ID = ?";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(1));
				java.sql.ResultSet rs1=pst1.executeQuery();
				if(rs1.next()) {
					club.setClub_ID(rs1.getInt(1));
					club.setClub_name(rs1.getString(2));
					club.setClub_amount(rs1.getInt(3));
					club.setClub_remark(rs1.getString(4));
					club.setClub_proID(rs1.getInt(5));
					club.setClub_grade(rs1.getInt(6));
					club.setClub_picture(rs1.getBytes(7));
					club.setClub_status(rs1.getNString(8));
					result.add(club);
				}
			}
			pst.close();
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}
	//	public List<Beanuser_club> loadallspusclub(int club_ID) throws BusinessException {
//		List<Beanuser_club> result=new ArrayList<Beanuser_club>();
//		Connection conn=null;
//		try{
//			conn=DBUtil.getConnection();
//			String sql="select * from user_club where club_ID = ? and club_status ='审核中'";
//			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
//			pst.setInt(1,club_ID);
//			java.sql.ResultSet rs=pst.executeQuery();
//			while(rs.next()) {
//				Beanuser_club club=new Beanuser_club();
//				club.setC_user_ID(rs.getInt(1));
//				club.setClub_ID(rs.getInt(2));
//				club.setClub_status(rs.getString(3));
//				club.setClub_time(rs.getTime(4));
//				club.setUser_grade(rs.getString(5));
//				result.add(club);
//			}
//			pst.close();
//			rs.close();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		return result;
//
//	}
	public List<Beanclub_person> loadalluserclub(int club_ID) throws BusinessException {
		List<Beanclub_person> result=new ArrayList<Beanclub_person>();
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select c_userid,c_picture,c_name,club_time,user_grade,club_ID from user_club a,user b where b.c_userID=a.c_user_ID and club_ID = ? and club_status ='审核中' order by club_time DESC";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,club_ID);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beanclub_person club=new Beanclub_person();
				club.setC_userID(rs.getInt(1));
				club.setC_picture(rs.getBytes(2));
				club.setC_name(rs.getString(3));
				club.setUser_time(rs.getDate(4));
				club.setUser_grade(rs.getString(5));
				club.setClub_ID(rs.getInt(6));
				result.add(club);
			}
			pst.close();
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}
	public List<Beanuser> loadalluser(int id) throws BusinessException {
		List<Beanuser> result=new ArrayList<Beanuser>();
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select c_user_ID from user_club where club_ID = ? and club_status ='已通过' ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beanuser user=new Beanuser();
				String sql1="select * from user where c_userID = ? and c_deletetime is null";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(1));
				java.sql.ResultSet rs1=pst1.executeQuery();
				if(rs1.next()){
					user.setC_userID(rs1.getInt(1));
					user.setC_number(rs1.getString(2));
					user.setC_pwd(rs1.getString(3));
					user.setC_creationtime(rs1.getTimestamp(4));
					user.setC_deletetime(rs1.getTimestamp(5));
					user.setC_grade(rs1.getString(6));
					user.setC_brithday(rs1.getTimestamp(7));
					user.setC_sex(rs1.getString(8));
					user.setC_name(rs1.getString(9));
					user.setC_sno(rs1.getString(10));
					user.setC_stugrade(rs1.getString(11));
					user.setC_major(rs1.getString(12));
					user.setC_mail(rs1.getString(13));
					result.add(user);
				}
			}
			pst.close();
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}
	public List<Beanclub_person> loadalluser_club(int id) throws BusinessException {
		List<Beanclub_person> result=new ArrayList<Beanclub_person>();
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select c_name,user_grade,c_picture,c_userID,club_ID from user_club a,user b where a.c_user_ID=b.c_userID and  club_ID=? and club_status ='已通过'  order by user_grade DESC";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				Beanclub_person a=new Beanclub_person();
				a.setC_name(rs.getString(1));
				a.setUser_grade(rs.getString(2));
				a.setC_picture(rs.getBytes(3));
				a.setC_userID(rs.getInt(4));
				a.setClub_ID(rs.getInt(5));
				result.add(a);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		Log.e("dsf",""+result.size());
		return result;

	}
	public List<Beanuser> loadallspuser(int id) throws BusinessException {
		List<Beanuser> result=new ArrayList<Beanuser>();
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="select c_user_ID from user_club where club_ID = ? and club_status ='审核中' ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beanuser user=new Beanuser();
				String sql1="select * from user where c_userID = ? and c_deletetime is null";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(1));
				java.sql.ResultSet rs1=pst1.executeQuery();
				if(rs1.next()){
					user.setC_userID(rs1.getInt(1));
					user.setC_number(rs1.getString(2));
					user.setC_pwd(rs1.getString(3));
					user.setC_creationtime(rs1.getTimestamp(4));
					user.setC_deletetime(rs1.getTimestamp(5));
					user.setC_grade(rs1.getString(6));
					user.setC_brithday(rs1.getTimestamp(7));
					user.setC_sex(rs1.getString(8));
					user.setC_name(rs1.getString(9));
					user.setC_sno(rs1.getString(10));
					user.setC_stugrade(rs1.getString(11));
					user.setC_major(rs1.getString(12));
					user.setC_mail(rs1.getString(13));
					result.add(user);
				}
			}
			pst.close();
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}
	public void adduserclub(int clubid) throws BusinessException {
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="insert into user_club values(?,?,'������',now())";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Beanuser.currentLoginUser.getC_userID());
			pst.setInt(2, clubid);
			pst.execute();
			pst.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void modifyuserclub(int clubid,int userid,String choose) throws BusinessException {
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="update user_club set club_status = ?,set club_time = now() where c_user_ID = ? and club_ID = ? and club_status ='������' ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, choose);
			pst.setInt(2, userid);
			pst.setInt(3, clubid);
			pst.execute();
			pst.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public boolean getuser_club(int user_ID, int club_ID) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "Select * from user_club where c_user_ID=? and club_ID=? and club_status='审核中' ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_ID);
			pst.setInt(2, club_ID);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void deleteuser_club(int user_ID, int club_ID) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from user_club where c_user_ID=? and club_ID=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_ID);
			pst.setInt(2, club_ID);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean getuser_clubpro(int user_ID, int club_ID) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "Select * from user_club where c_user_ID=? and club_ID=? and user_grade=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user_ID);
			pst.setInt(2, club_ID);
			pst.setString(3,"社长");
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void adduser_club(Beanuser user, Beanclub club) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="INSERT INTO user_club(c_user_ID,club_ID,club_status,club_time,user_grade) values(?,?,?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, user.getC_userID());
			pst.setInt(2, club.getClub_ID());
			pst.setString(3, "审核中");
			pst.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setString(5,"社员" );
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void zhuanuser_club(int user_id1,int user_id2,int club_id) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE user_club SET user_grade='社长' WHERE club_id=? and c_user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, club_id);
			pst.setInt(2, user_id1);
			pst.execute();
			pst.close();
			sql="UPDATE user_club SET user_grade='社员' WHERE club_id=? and c_user_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, club_id);
			pst.setInt(2, user_id2);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void modifyuserclub1(int clubid,int userid,String choose) throws BusinessException {
		Connection conn=null;
		try{
			conn=DBUtil.getConnection();
			String sql="update user_club set club_status = ? where c_user_ID = ? and club_ID = ? and club_status ='审核中' ";
			Log.e("hpa",""+clubid);
			Log.e("hpa1",""+userid);
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, choose);
			pst.setInt(2, userid);
			pst.setInt(3, clubid);
			pst.execute();
			pst.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<Beanclub_person> moselectperson(String name,int club_ID) throws BusinessException {
		Connection conn=null;
		List<Beanclub_person> user1=new ArrayList<Beanclub_person>();
		try{
			conn=DBUtil.getConnection();
			String sql="select c_name,user_grade,c_picture,c_userID from user_club a,user b where a.c_user_ID=b.c_userID and  club_ID=? and club_status ='已通过'and c_name like '%"+name+"%'";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,club_ID);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				Beanclub_person user =new Beanclub_person();
				user.setC_name(rs.getString(1));
				user.setUser_grade(rs.getString(2));
				user.setC_picture(rs.getBytes(3));
				user.setC_userID(rs.getInt(4));
				user1.add(user);
			}
			pst.close();
			rs.close();
			return user1;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
