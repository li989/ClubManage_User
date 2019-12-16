package com.example.club.control.example;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.example.club.model.Beanawards;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;
import com.example.club.util.BusinessException;
import com.example.club.util.*;
import com.example.club.util.DbException;

public class ClubManager {
	public void addClub(String name,String remark,int pro_id,String type,byte[] img) throws BaseException {
		Connection conn=null;
		try {
			int c=1;
			conn=DBUtil.getConnection();
			String sql="select * from club where club_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("社团名已存在");
			rs.close();
			pst.close();
			sql="SELECT MAX(club_ID) from club";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next())c=rs.getInt(1)+1;
			sql="INSERT INTO club(club_ID,club_name,club_remark,club_proID,club_grade,club_status,club_picture,club_createtime,club_type) values(?,?,?,?,0,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, c);
			pst.setString(2, name);
			pst.setString(3, remark);
			pst.setInt(4,Beanuser.currentLoginUser.getC_userID() );
			pst.setString(5, "审核中");
			pst.setBytes(6, img);
			pst.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setString(8, type);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Beanclub> loadAll() throws BaseException {
		List<Beanclub> result=new ArrayList<Beanclub>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from club where club_status=? and club_deletetime is null";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "已通过");
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beanclub a=new Beanclub();
				a.setClub_ID(rs.getInt(1));
				a.setClub_name(rs.getString(2));
				a.setClub_remark(rs.getString(3));
				a.setClub_proID(rs.getInt(4));
				a.setClub_grade(rs.getInt(5));
				a.setClub_picture(rs.getBytes(6));
				a.setClub_createtime(rs.getTimestamp(7));
				a.setClub_deletetime(rs.getTimestamp(8));
				a.setClub_status(rs.getString(9));
				a.setClub_type(rs.getString(10));
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

	public List<Beanclub> loadAllpx() throws BaseException {
		List<Beanclub> result=new ArrayList<Beanclub>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from club where club_status=? and club_deletetime is null order by club_grade DESC";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "已通过");
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beanclub a=new Beanclub();
				a.setClub_ID(rs.getInt(1));
				a.setClub_name(rs.getString(2));
				a.setClub_remark(rs.getString(3));
				a.setClub_proID(rs.getInt(4));
				a.setClub_grade(rs.getInt(5));
				a.setClub_picture(rs.getBytes(6));
				a.setClub_createtime(rs.getTimestamp(7));
				a.setClub_deletetime(rs.getTimestamp(8));
				a.setClub_status(rs.getString(9));
				a.setClub_type(rs.getString(10));
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

	//	public List<Beanclub> loadAllexaming(String status) throws BaseException {
	//		List<Beanclub> result=new ArrayList<Beanclub>();
	//		Connection conn=null;
	//		try {
	//			conn=DBUtil.getConnection();
	//			String sql="select * from club_introducein where club_status=? ";
	//			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
	//			pst.setString(1,status);
	//			java.sql.ResultSet rs=pst.executeQuery();
	//		while(rs.next()) {
	//			Beanclub a=new Beanclub();
	//			a.setClub_ID(rs.getInt(1));
	//			a.setClub_name(rs.getString(2));
	//			a.setClub_amount(rs.getInt(3));
	//			a.setClub_remark(rs.getString(4));
	//			a.setClub_proID(rs.getInt(5));
	//			a.setClub_grade(rs.getInt(6));
	//			a.setClub_picture(rs.getBytes(7));
	//			a.setClub_stutus(rs.getNString(8));
	//			result.add(a);
	//		}
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//			throw new DbException(e);
	//		}
	//		finally{
	//			if(conn!=null)
	//				try {
	//					conn.close();
	//				} catch (SQLException e) {
	//					// TODO Auto-generated catch block
	//					e.printStackTrace();
	//				}
	//		}
	//		return result;
	//	}


	public void ExamineClub(int club_ID,String status) throws BaseException {
		Connection conn=null;
		try {
			int c=0;
			conn=DBUtil.getConnection();
			String sql="UPDATE club SET club_status=? where club_ID=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, status);
			pst.setInt(2,club_ID);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Beanclub> selectClub(String name) throws BaseException {
		List<Beanclub> result=new ArrayList<Beanclub>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from club where club_name like ? and club_status=? and club_deletetime is null";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+name+"%");
			pst.setString(2, "已通过");
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beanclub a=new Beanclub();
				a.setClub_ID(rs.getInt(1));
				a.setClub_name(rs.getString(2));
				a.setClub_remark(rs.getString(3));
				a.setClub_proID(rs.getInt(4));
				a.setClub_grade(rs.getInt(5));
				a.setClub_picture(rs.getBytes(6));
				a.setClub_createtime(rs.getTimestamp(7));
				a.setClub_deletetime(rs.getTimestamp(8));
				a.setClub_status(rs.getString(9));
				a.setClub_type(rs.getString(10));
				result.add(a);
			}
			rs.close();
			pst.close();
			return result;
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
	}
	public Beanclub getClub(int id) throws BaseException {
		Beanclub a=new Beanclub();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from club where club_ID=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				a.setClub_ID(rs.getInt(1));
				a.setClub_name(rs.getString(2));
				a.setClub_remark(rs.getString(3));
				a.setClub_proID(rs.getInt(4));
				a.setClub_grade(rs.getInt(5));
				a.setClub_picture(rs.getBytes(6));
				a.setClub_createtime(rs.getTimestamp(7));
				a.setClub_deletetime(rs.getTimestamp(8));
				a.setClub_status(rs.getString(9));
				a.setClub_type(rs.getString(10));
			}
			sql="select count(*) from user_club where club_ID=? and club_status='已通过'";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()) {
				a.setClub_amount(rs.getInt(1));
			}
			rs.close();
			pst.close();
			return a;
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
	}

	public void deleteClub(int club_ID) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select club_ID from clubwhere club_ID=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, club_ID);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("社团不存在");
			rs.close();
			pst.close();
			pst=conn.prepareStatement("update club set club_deletetime=? where club_ID=?");
			pst.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setInt(2, club_ID);
			pst.execute();
			pst.close();

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
	}

	public List<Beanclub> selecthnClub() throws BaseException {
		List<Beanclub> result=new ArrayList<Beanclub>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from club where club_status='已通过' and club_deletetime is null order by club_grade Desc";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			int i=0;
			while(rs.next()&&i<5) {
				Beanclub a=new Beanclub();
				a.setClub_ID(rs.getInt(1));
				a.setClub_name(rs.getString(2));
				a.setClub_remark(rs.getString(3));
				a.setClub_proID(rs.getInt(4));
				a.setClub_grade(rs.getInt(5));
				a.setClub_picture(rs.getBytes(6));
				a.setClub_createtime(rs.getTimestamp(7));
				a.setClub_deletetime(rs.getTimestamp(8));
				a.setClub_status(rs.getString(9));
				a.setClub_type(rs.getString(10));
				result.add(a);
				i++;
			}
			sql="select * from club where club_status='已通过' and club_deletetime is null order by club_createtime Desc";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			i=0;
			while(rs.next()&&i<5) {
				Beanclub a=new Beanclub();
				a.setClub_ID(rs.getInt(1));
				a.setClub_name(rs.getString(2));
				a.setClub_remark(rs.getString(3));
				a.setClub_proID(rs.getInt(4));
				a.setClub_grade(rs.getInt(5));
				a.setClub_picture(rs.getBytes(6));
				a.setClub_createtime(rs.getTimestamp(7));
				a.setClub_deletetime(rs.getTimestamp(8));
				a.setClub_status(rs.getString(9));
				a.setClub_type(rs.getString(10));
				result.add(a);
				i++;
			}
			rs.close();
			pst.close();
			return result;
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
	}

	public List<Beanclub> selecthnmoreClub() throws BaseException {
		List<Beanclub> result=new ArrayList<Beanclub>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from club where club_status='已通过' and club_deletetime is null order by club_grade Desc";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			int i=0;
			while(rs.next()&&i<20) {
				Beanclub a=new Beanclub();
				a.setClub_ID(rs.getInt(1));
				a.setClub_name(rs.getString(2));
				a.setClub_remark(rs.getString(3));
				a.setClub_proID(rs.getInt(4));
				a.setClub_grade(rs.getInt(5));
				a.setClub_picture(rs.getBytes(6));
				a.setClub_createtime(rs.getTimestamp(7));
				a.setClub_deletetime(rs.getTimestamp(8));
				a.setClub_status(rs.getString(9));
				a.setClub_type(rs.getString(10));
				result.add(a);
				i++;
			}
			sql="select * from club where club_status='已通过' and club_deletetime is null order by club_createtime Desc";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			i=0;
			while(rs.next()&&i<20) {
				Beanclub a=new Beanclub();
				a.setClub_ID(rs.getInt(1));
				a.setClub_name(rs.getString(2));
				a.setClub_remark(rs.getString(3));
				a.setClub_proID(rs.getInt(4));
				a.setClub_grade(rs.getInt(5));
				a.setClub_picture(rs.getBytes(6));
				a.setClub_createtime(rs.getTimestamp(7));
				a.setClub_deletetime(rs.getTimestamp(8));
				a.setClub_status(rs.getString(9));
				a.setClub_type(rs.getString(10));
				result.add(a);
				i++;
			}
			rs.close();
			pst.close();
			return result;
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
	}

	public List<Beanclub> selectclaClub(String cla) throws BaseException {
		List<Beanclub> result=new ArrayList<Beanclub>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from club where club_status='已通过' and club_deletetime is null and club_type = ? order by club_grade Desc";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,cla);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beanclub a=new Beanclub();
				a.setClub_ID(rs.getInt(1));
				a.setClub_name(rs.getString(2));
				a.setClub_remark(rs.getString(3));
				a.setClub_proID(rs.getInt(4));
				a.setClub_grade(rs.getInt(5));
				a.setClub_picture(rs.getBytes(6));
				a.setClub_createtime(rs.getTimestamp(7));
				a.setClub_deletetime(rs.getTimestamp(8));
				a.setClub_status(rs.getString(9));
				a.setClub_type(rs.getString(10));
				result.add(a);
			}
			return result;
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
	}

	public List<Beanclub> getsixhotClub() throws BaseException {
		List<Beanclub> result=new ArrayList<Beanclub>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from club where  club_status='已通过' and club_deletetime is null";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			int i=0;
			while(rs.next()) {
				if (i>=6)break;
				Beanclub a=new Beanclub();
				a.setClub_ID(rs.getInt(1));
				a.setClub_name(rs.getString(2));
				a.setClub_remark(rs.getString(3));
				a.setClub_proID(rs.getInt(4));
				a.setClub_grade(rs.getInt(5));
				a.setClub_picture(rs.getBytes(6));
				a.setClub_createtime(rs.getTimestamp(7));
				a.setClub_deletetime(rs.getTimestamp(8));
				a.setClub_status(rs.getString(9));
				a.setClub_type(rs.getString(10));
				result.add(a);
				i++;
			}
			rs.close();
			pst.close();
			return result;
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
	}

	public List<Beanclub> selecthClub(String more) throws DbException {
		List<Beanclub> result=new ArrayList<Beanclub>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();

			String sql="select * from club where club_status='已通过' and club_deletetime is null order by club_grade Desc ";
			if (more.equals("推荐社团"))
				sql="select * from club where club_status='已通过' and club_deletetime is null order by club_createtime Desc ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			int i=0;
			while(rs.next()&&i<20) {
				Beanclub a=new Beanclub();
				a.setClub_ID(rs.getInt(1));
				a.setClub_name(rs.getString(2));
				a.setClub_remark(rs.getString(3));
				a.setClub_proID(rs.getInt(4));
				a.setClub_grade(rs.getInt(5));
				a.setClub_picture(rs.getBytes(6));
				a.setClub_createtime(rs.getTimestamp(7));
				a.setClub_deletetime(rs.getTimestamp(8));
				a.setClub_status(rs.getString(9));
				a.setClub_type(rs.getString(10));
				result.add(a);
				i++;
			}
			return result;
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
	}
	public String inclub(int userid,int clubid){
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_grade from user_club where club_status='已通过' and club_ID=? and c_user_ID=?  ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,clubid);
			pst.setInt(2,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) return null;
			else return rs.getString(1);

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
