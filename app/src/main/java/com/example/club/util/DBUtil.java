package com.example.club.util;

import android.nfc.Tag;
import android.util.Log;

import java.sql.Connection;
import java.sql.SQLException;


public class DBUtil {
    private static final String jdbcUrl="jdbc:mysql://rm-bp16983xfqbe1k12nlo.mysql.rds.aliyuncs.com:3306/club";
    private static final String dbUser="root";
    private static final String dbPwd="Jin123456";

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws java.sql.SQLException{
        Connection conn=null;
        conn=(Connection)java.sql.DriverManager.getConnection(jdbcUrl, dbUser,dbPwd);
        return conn;
    }
}

