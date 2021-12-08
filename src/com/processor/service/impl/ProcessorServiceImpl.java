package com.processor.service.impl;

import com.processor.entity.LoanDO;
import com.processor.req.ProcessorRequest;
import com.processor.res.TableDTO;
import com.processor.service.ProcessorService;
import com.processor.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ProcessorServiceImpl implements ProcessorService {
    @Override
    public TableDTO retrieveProcessor(ProcessorRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from data where user_name = '").append(request.getName()).append("'");
        if(request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())){
            sql.append(" and name like '%" + request.getSearchKey() + "%'");
        }
        sql.append("order by id desc limit ").append(request.getStart()).append(",").append(request.getPageSize());
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO returnDTO = new TableDTO();
        try{
            Vector<Vector<Object>> data = new Vector<>();
            int i = 0;
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                Vector<Object> oneRecord = new Vector<>();
//                int id = rs.getInt("id");
                String loan_number = rs.getString("loan_number");
                String name = rs.getString("name");
                String rate = rs.getString("rate");
                String loan_amount = rs.getString("loan_amount");
                String program = rs.getString("program");
                String expiration = rs.getString("expiration");
//                oneRecord.addElement(id);
                oneRecord.addElement(loan_number);
                oneRecord.addElement(name);
                oneRecord.addElement(rate);
                oneRecord.addElement(loan_amount);
                oneRecord.addElement(program);
                oneRecord.addElement(expiration);
                data.addElement(oneRecord);
                i++;
            }
            returnDTO.setData(data);
            returnDTO.setTotalCount(i);
            return returnDTO;
//            sql.setLength(0);
//            sql.append("select count(*) from data ");
//            if(request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())){
//                sql.append(" where name like '%" + request.getSearchKey() + "%'");
//            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public boolean add(LoanDO loanDO) {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into data(user_name,loan_number,name,rate,loan_amount,program,expiration) ");
        sql.append(" values(?,?,?,?,?,?,?) ");
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,loanDO.getUser_name());
            ps.setString(2,loanDO.getLoan_number());
            ps.setString(3,loanDO.getBor_name());
            ps.setString(4,loanDO.getRate());
            ps.setString(5,loanDO.getLoan_amount());
            ps.setString(6,loanDO.getProgram());
            ps.setString(7, loanDO.getExpiration());
            return ps.executeUpdate() == 1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public boolean delete(Vector<Object> selectedLoans) {
        for(int i = 0; i < selectedLoans.size(); i++){
            StringBuilder sql = new StringBuilder("delete from data where loan_number = ");
            sql.append(selectedLoans.get(i).toString());
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = DBUtil.getConn();
                ps = conn.prepareStatement(sql.toString());
                return ps.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.closePs(ps);
                DBUtil.closeConn(conn);
            }
        }
        return false;
    }

    @Override
    public LoanDO getByLoanNo(String selectedLoan) {
        StringBuilder sql = new StringBuilder("select * from data where loan_number = ");
        sql.append(selectedLoan);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LoanDO loanDO = new LoanDO();
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String loan_number = rs.getString("loan_number");
                String name = rs.getString("name");
                String rate = rs.getString("rate");
                String loan_amount = rs.getString("loan_amount");
                String program = rs.getString("program");
                String expiration = rs.getString("expiration");
                loanDO.setId(id);
                loanDO.setLoan_number(loan_number);
                loanDO.setBor_name(name);
                loanDO.setRate(rate);
                loanDO.setLoan_amount(loan_amount);
                loanDO.setProgram(program);
                loanDO.setExpiration(expiration);
            }
            return loanDO;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public boolean update(LoanDO loanDO) {
        StringBuilder sql = new StringBuilder();
        sql.append(" update data set loan_number=?,name=?,rate=?,loan_amount=?,program=?,expiration=? ");
        sql.append(" where id = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,loanDO.getLoan_number());
            ps.setString(2,loanDO.getBor_name());
            ps.setString(3,loanDO.getRate());
            ps.setString(4,loanDO.getLoan_amount());
            ps.setString(5,loanDO.getProgram());
            ps.setString(6, loanDO.getExpiration());
            ps.setInt(7, loanDO.getId());
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
