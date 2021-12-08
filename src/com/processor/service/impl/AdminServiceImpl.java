package com.processor.service.impl;

import com.processor.entity.AdminDO;
import com.processor.service.AdminService;
import com.processor.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminServiceImpl implements AdminService {

    @Override
    public boolean validateAdmin(AdminDO adminDO) {
        String userName = adminDO.getUserName();
        String pwdParam = adminDO.getPwd();
        String sql = "select password from login where user_name = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try{
            conn = DBUtil.getConn();
            if(conn == null){ return false;}
            ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            resultSet = ps.executeQuery();
            while(resultSet.next()){
                String pwd = resultSet.getString(1);
                if(pwdParam.equals(pwd))
                    return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(resultSet);
            DBUtil.closeConn(conn);
            DBUtil.closePs(ps);
        }
        return false;
    }

    @Override
    public boolean insertAdmin(AdminDO adminDO) {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into login(user_name,password )");
        sql.append(" values(?,?) ");
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,adminDO.getUserName());
            ps.setString(2,adminDO.getPwd());
            return ps.executeUpdate() == 1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }
}
