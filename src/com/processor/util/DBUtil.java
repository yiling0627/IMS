package com.processor.util;

import java.sql.*;

public class DBUtil {
    private static String dbUrl = "jdbc:mysql://localhost:3306/information?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static String dbUserName = "root";
    private static String dbPassword = "lyl09050627m";
    private static String jdbcName = "java.sql.Connection";
    /*
     * Database connection
     */
    static {
        try {
            Class.forName(jdbcName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        try {
            return DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /*
     * Close database connection
     */
    public static void closeConn(java.sql.Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePs(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeRs(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
