package com.example.club.control.example;

import android.util.Log;

import com.example.club.model.Beanclubnotice;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;
import com.example.club.util.BusinessException;
import com.example.club.util.DBUtil;
import com.example.club.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClubNoticeManager {
    public Beanclubnotice select_aClubnotice(int ID) throws BaseException {
        Beanclubnotice a=new Beanclubnotice();
        Connection conn=null;
        try {
            conn= DBUtil.getConnection();
            String sql="select * from clubnotice where clubnotice_ID=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, ID);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()) {
                a.setClubnotice_ID(rs.getInt(1));
                a.setClub_ID(rs.getInt(2));
                a.setClubnotice_title(rs.getString(3));
                a.setClubnotice_content(rs.getString(4));
                a.setClubnotice_start_time(rs.getDate(5));
                a.setClubnotice_picture(rs.getBytes(6));
                a.setClubnotice_number(rs.getInt(7));
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
    public List<Beanclubnotice> selectClubnotice(int ID) throws BaseException {
        List<Beanclubnotice> result=new ArrayList<Beanclubnotice>();
        Connection conn=null;
        try {
            conn= DBUtil.getConnection();
            String sql="select * from clubnotice where club_ID=?  ORDER BY clubnotice_start_time DESC";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, ID);
            java.sql.ResultSet rs=pst.executeQuery();
            while(rs.next()) {
                Beanclubnotice a=new Beanclubnotice();
                a.setClubnotice_ID(rs.getInt(1));
                a.setClub_ID(rs.getInt(2));
                a.setClubnotice_title(rs.getString(3));
                a.setClubnotice_content(rs.getString(4));
                a.setClubnotice_start_time(rs.getDate(5));
                a.setClubnotice_picture(rs.getBytes(6));
                a.setClubnotice_number(rs.getInt(7));
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
    public void addClubnotice(int club_ID,String title,String content,byte[] img) throws BaseException {
        Connection conn=null;
        try {
            int c=1;
            conn=DBUtil.getConnection();
            String sql="SELECT MAX(clubnotice_ID) from clubnotice";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next())c=rs.getInt(1)+1;
            sql="INSERT INTO clubnotice(clubnotice_ID,club_ID,clubnotice_title,clubnotice_content,clubnotice_start_time,clubnotice_picture,clubnotice_number) values(?,?,?,?,?,?,0)";
            pst=conn.prepareStatement(sql);
            pst.setInt(1, c);
            pst.setInt(2, club_ID);
            pst.setString(3, title);
            pst.setString(4, content);
            pst.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
            pst.setBytes(6,img);
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateClubnoticen(int clubnotice_ID) throws BaseException {
        Connection conn=null;
        try {
            int c=1;
            conn=DBUtil.getConnection();
            String sql="SELECT clubnotice_number from clubnotice where clubnotice_ID=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,clubnotice_ID);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next())c=rs.getInt(1)+1;
            sql="UPDATE clubnotice SET clubnotice_number=? WHERE clubnotice_ID=?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1, c);
            pst.setInt(2, clubnotice_ID);
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
