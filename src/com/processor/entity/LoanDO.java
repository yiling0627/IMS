package com.processor.entity;

import java.util.Date;

public class LoanDO {
    private int id;
    private String user_name;
    private String loan_number;
    private String bor_name;
    private String rate;
    private String loan_amount;
    private String program;
    private String expiration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLoan_number() {
        return loan_number;
    }

    public void setLoan_number(String loan_number) {
        this.loan_number = loan_number;
    }

    public String getBor_name() {
        return bor_name;
    }

    public void setBor_name(String bor_name) {
        this.bor_name = bor_name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
