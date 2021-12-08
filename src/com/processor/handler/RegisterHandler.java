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

public class RegisterHandler implements ActionListener {
    private RegisterView registerView;

    public RegisterHandler (RegisterView registerView){
        this.registerView = registerView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("Submit".equals(text)){
            register();
        } else if ("Return".equals(text)){
            registerView.dispose();
            new LoginView();
        }
    }

    private void register() {
        String user = registerView.getUserTxt().getText();
        char [] chars = registerView.getPwdField().getPassword();
        char [] charsC = registerView.getPwdCField().getPassword();
        if(user == null || "".equals(user.trim()) || chars == null ){
            JOptionPane.showMessageDialog(registerView,"Please enter username or password!");
            return;
        }
        String pwd = new String(chars);
        String pwdC = new String(charsC);
        if(!pwd.equals(pwdC)){
            JOptionPane.showMessageDialog(registerView,"Password not match!");
            return;
        }

        //Search Database
        AdminService adminService = new AdminServiceImpl();
        AdminDO adminDO = new AdminDO();
        adminDO.setUserName(user);
        adminDO.setPwd(pwd);
        boolean flag = adminService.insertAdmin(adminDO);
        if(flag){
            new MainView(user);
            registerView.dispose();
        } else{
            JOptionPane.showMessageDialog(registerView,"User exist!");
        }
    }
}
