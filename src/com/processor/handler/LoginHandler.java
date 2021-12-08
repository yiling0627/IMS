package com.processor.handler;

import com.processor.entity.AdminDO;
import com.processor.service.AdminService;
import com.processor.service.impl.AdminServiceImpl;
import com.processor.view.LoginView;
import com.processor.view.MainView;
import com.processor.view.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginHandler extends KeyAdapter implements ActionListener {

    private LoginView loginView;

    public LoginHandler (LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("Login".equals(text)){
            login();
        } else if ("Register".equals(text)){
//            loginView.getUserTxt().setText("");
//            loginView.getPwdField().setText("");
            new RegisterView();
            loginView.dispose();
        }
    }

    private void login() {
        String user = loginView.getUserTxt().getText();
        char [] chars = loginView.getPwdField().getPassword();
        if(user == null || "".equals(user.trim()) || chars == null ){
            JOptionPane.showMessageDialog(loginView,"Please enter username or password!");
            return;
        }
        String pwd = new String(chars);

        //Search Database
        AdminService adminService = new AdminServiceImpl();
        AdminDO adminDO = new AdminDO();
        adminDO.setUserName(user);
        adminDO.setPwd(pwd);
        boolean flag = adminService.validateAdmin(adminDO);
        if(flag){
            new MainView(user);
            loginView.dispose();
        } else{
            JOptionPane.showMessageDialog(loginView,"Wrong password!");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_ENTER == e.getKeyCode()){
            login();
        };
    }
}
