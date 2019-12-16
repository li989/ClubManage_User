package com.example.club.control.example;

import com.example.club.model.Beanclub;
import com.example.club.model.Beanplace;
import com.example.club.util.BaseException;
import com.example.club.util.DBUtil;
import com.example.club.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceManager {
    public Beanplace getPlacea(int id) throws BaseException {
        Beanplace a=new Beanplace();
        Connection conn=null;
        try {
            conn= DBUtil.getConnection();
            String sql="select * from place where place_ID=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, id);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()) {
                a.setPlace_ID(rs.getInt(1));
                a.setPlace_name(rs.getString(2));
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
    public List<Beanplace> getPlaceall() throws BaseException {
        List<Beanplace> place=new ArrayList<Beanplace>();
        Connection conn=null;
        try {
            conn= DBUtil.getConnection();
            String sql="select * from place";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs=pst.executeQuery();
            while (rs.next()) {
                Beanplace a =new Beanplace();
                a.setPlace_ID(rs.getInt(1));
                a.setPlace_name(rs.getString(2));
                place.add(a);
            }
            rs.close();
            pst.close();
            return place;
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
}
