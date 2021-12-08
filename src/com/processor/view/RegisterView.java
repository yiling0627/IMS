package com.processor.view;

import com.processor.handler.RegisterHandler;

import javax.swing.*;
import java.awt.*;


public class RegisterView extends JFrame{
    JLabel nameLabel = new JLabel("Information Management System",JLabel.CENTER);

    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel userNameLabel = new JLabel("UserID:");
    JTextField userTxt = new JTextField();
    JLabel pwdLabel = new JLabel("Password:");
    JPasswordField pwdField = new JPasswordField();
    JLabel pwdCLabel = new JLabel("Re-Type Password:");
    JPasswordField pwdCField = new JPasswordField();
    JButton submitBtn = new JButton("Submit");
    JButton returnBtn = new JButton("Return");

    RegisterHandler registerHandler;

    public RegisterView() {
        super("Information Management System");
        registerHandler = new RegisterHandler(this);

        Container contentPane = getContentPane();
        Font font1 = new Font("Cooper Black", Font.BOLD, 25);
        Font font2 = new Font("Cooper Black", Font.PLAIN, 20);
        nameLabel.setFont(font1);
        userNameLabel.setFont(font2);
        userTxt.setFont(font2);
        pwdField.setFont(font2);
        pwdLabel.setFont(font2);
        pwdCLabel.setFont(font2);
        pwdCField.setFont(font2);
        submitBtn.setFont(font2);
        returnBtn.setFont(font2);


        nameLabel.setPreferredSize(new Dimension(0,80));
        userTxt.setPreferredSize(new Dimension(200, 30));
        pwdField.setPreferredSize(new Dimension(200, 30));
        pwdCField.setPreferredSize(new Dimension(200, 30));


        centerPanel.add(userNameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdField);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdCField);
        centerPanel.add(pwdCLabel);
        centerPanel.add(submitBtn);
        centerPanel.add(returnBtn);
        submitBtn.addActionListener(registerHandler);
        returnBtn.addActionListener(registerHandler);

        //SpringLayout
        layoutCenter();


        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);


        setIconImage(new ImageIcon("src/com/processor/image/icon.png").getImage());
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    private void layoutCenter() {
        Spring ChildWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel), Spring.width(userTxt)), Spring.constant(20));
        int offsetX = ChildWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetX,SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,40,SpringLayout.NORTH, centerPanel);

        springLayout.putConstraint(SpringLayout.WEST,userTxt,20,SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userTxt,0,SpringLayout.NORTH, userNameLabel);

        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,20,SpringLayout.SOUTH, userNameLabel);

        springLayout.putConstraint(SpringLayout.WEST,pwdField,20,SpringLayout.EAST, pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdField,0,SpringLayout.NORTH, pwdLabel);

        springLayout.putConstraint(SpringLayout.EAST,pwdCLabel,0,SpringLayout.EAST, pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdCLabel,20,SpringLayout.SOUTH, pwdLabel);

        springLayout.putConstraint(SpringLayout.WEST,pwdCField,20,SpringLayout.EAST, pwdCLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdCField,0,SpringLayout.NORTH, pwdCLabel);

        springLayout.putConstraint(SpringLayout.EAST,submitBtn,10,SpringLayout.EAST, pwdCLabel);
        springLayout.putConstraint(SpringLayout.NORTH,submitBtn,20,SpringLayout.SOUTH, pwdCLabel);

        springLayout.putConstraint(SpringLayout.WEST,returnBtn,100,SpringLayout.EAST, submitBtn);
        springLayout.putConstraint(SpringLayout.NORTH,returnBtn,0,SpringLayout.NORTH, submitBtn);
    }

    public JTextField getUserTxt() {
        return userTxt;
    }
    public JPasswordField getPwdField() {
        return pwdField;
    }
    public JPasswordField getPwdCField() {
        return pwdCField;
    }

    public static void main(String[] args) {
        new RegisterView();
    }
}
