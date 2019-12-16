package com.example.club.control.example;

import com.example.club.model.Beanlabel;
import com.example.club.util.BaseException;
import com.example.club.util.BusinessException;
import com.example.club.util.DBUtil;
import com.example.club.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LabelManager {
    public void addLabel(String name) throws BaseException {
    Connection conn=null;
        try {
            int c=1;
            conn=DBUtil.getConnection();
            String sql="select * from label where label_name=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,name);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()) throw new BusinessException("标签已存在");
            rs.close();
            pst.close();
            sql="SELECT MAX(label_ID) from label";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next())c=rs.getInt(1)+1;
            sql="INSERT INTO label(label_ID,label_name) values(?,?)";
            pst=conn.prepareStatement(sql);
            pst.setInt(1, c);
            pst.setString(2, name);
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public List<Beanlabel> loadAll() throws BaseException {
		List<Beanlabel> result=new ArrayList<Beanlabel>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from label";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			Beanlabel a=new Beanlabel();
			a.setID(rs.getInt(1));
			a.setName(rs.getString(2));
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
	public void deleteLabel(Beanlabel label) throws BaseException {
	Connection conn=null;
	try {
		conn=DBUtil.getConnection();
		String sql="select label_ID from label where label_ID=?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, label.getID());
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("标签不存在");
		rs.close();
		pst.close();
		pst=conn.prepareStatement("delete from label where label_ID=?");
		pst.setInt(1, label.getID());
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
	//��������������
	public List<Beanlabel> selectLabel(String name) throws BaseException {
		List<Beanlabel> result=new ArrayList<Beanlabel>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from label where label_name like ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+name+"%");
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beanlabel a=new Beanlabel();
				a.setID(rs.getInt(1));
				a.setName(rs.getString(2));
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
}

