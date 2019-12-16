package com.example.club.control.example;

import com.example.club.model.Beanactivity;
import com.example.club.model.Beanawards;
import com.example.club.util.BaseException;
import com.example.club.util.DBUtil;
import com.example.club.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityManager {
    public List<Beanactivity> loadAllclub(int ID) throws BaseException {
        List<Beanactivity> result=new ArrayList<Beanactivity>();
        Connection conn=null;
        try {
            conn= DBUtil.getConnection();
            String sql="select * from activity where club_ID=? and activity_status=?  ORDER BY activity_start_time DESC" ;
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, ID);
            pst.setString(2, "已通过");
            java.sql.ResultSet rs=pst.executeQuery();
            while(rs.next()) {
                Beanactivity a=new Beanactivity();
                a.setActivity_ID(rs.getInt(1));
                a.setClub_ID(rs.getInt(2));
                a.setPlace_ID(rs.getInt(3));
                a.setActivity_name(rs.getString(4));
                a.setActivity_start_time(rs.getDate(5));
                a.setActivity_finish_time(rs.getDate(6));
                a.setActivity_grade(rs.getInt(7));
                a.setActivity_status(rs.getString(8));
                a.setActivity_picture(rs.getBytes(9));
                a.setActivity_centent(rs.getString(10));
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
    public Beanactivity loadactivitya(int ID) throws BaseException {
        Beanactivity a=new Beanactivity();
        Connection conn=null;
        try {
            conn=DBUtil.getConnection();
            String sql="select * from activity where activity_ID=?" ;
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, ID);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()) {
                a.setActivity_ID(rs.getInt(1));
                a.setClub_ID(rs.getInt(2));
                a.setPlace_ID(rs.getInt(3));
                a.setActivity_name(rs.getString(4));
                a.setActivity_start_time(rs.getDate(5));
                a.setActivity_centent(rs.getString(10));
                a.setActivity_finish_time(rs.getDate(6));
                a.setActivity_grade(rs.getInt(7));
                a.setActivity_status(rs.getString(8));
                a.setActivity_picture(rs.getBytes(9));
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
    public void addActivity(String name,int club_ID,int place_ID,String start_time,String finish_time,String centent,byte[] img ) throws BaseException {
        Connection conn=null;
        try {
            int c=1;
            conn= DBUtil.getConnection();
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;
            date1 = format1.parse(start_time);
            Date date2 = null;
            date2 = format1.parse(finish_time);
            java.sql.Date  sqlDate1 = new java.sql.Date(date1.getTime());
            java.sql.Date  sqlDate2 = new java.sql.Date(date2.getTime());
            String sql="SELECT MAX(activity_ID) from activity";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next())c=rs.getInt(1)+1;
            sql="INSERT INTO activity(activity_ID,club_ID,place_ID,activity_name,activity_start_time,activity_finish_time,activity_status,activity_picture,activity_centent) values(?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setInt(1, c);
            pst.setInt(2, club_ID);
            pst.setInt(3, place_ID);
            pst.setString(4,name);
            pst.setDate(5,sqlDate1);
            pst.setDate(6,sqlDate2);
            pst.setString(7,"审核中");
            pst.setBytes(8,img);
            pst.setString(9,centent);
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
