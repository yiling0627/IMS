package com.processor.view;

import com.processor.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Author Yiling
 */

public class LoginView extends JFrame {
    JLabel nameLabel = new JLabel("Information Management System",JLabel.CENTER);

    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel userNameLabel = new JLabel("UserID:");
    JTextField userTxt = new JTextField();
    JLabel pwdLabel = new JLabel("Password:");
    JPasswordField pwdField = new JPasswordField();
    JButton loginBtn = new JButton("Login");
    JButton registerBtn = new JButton("Register");

    SystemTray systemTray;
    TrayIcon trayIcon;

    LoginHandler loginHandler;

    public LoginView () {
        super("Information Management System");
        loginHandler = new LoginHandler(this);

        Container contentPane = getContentPane();
        Font font1 = new Font("Cooper Black", Font.BOLD, 25);
        Font font2 = new Font("Cooper Black", Font.PLAIN, 20);
        nameLabel.setFont(font1);
        userNameLabel.setFont(font2);
        userTxt.setFont(font2);
        pwdField.setFont(font2);
        pwdLabel.setFont(font2);
        loginBtn.setFont(font2);
        registerBtn.setFont(font2);


        nameLabel.setPreferredSize(new Dimension(0,80));
        userTxt.setPreferredSize(new Dimension(200, 30));
        pwdField.setPreferredSize(new Dimension(200, 30));


        centerPanel.add(userNameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdField);
        centerPanel.add(pwdLabel);
        centerPanel.add(loginBtn);
        loginBtn.addActionListener(loginHandler);
        loginBtn.addKeyListener(loginHandler);
        centerPanel.add(registerBtn);
        registerBtn.addActionListener(loginHandler);

        //SpringLayout
        layoutCenter();


        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);

        //Tray Setting
        if(SystemTray.isSupported()){
            systemTray = SystemTray.getSystemTray();
            trayIcon = new TrayIcon(new ImageIcon("src/com/processor/image/tray.png").getImage());
            trayIcon.setImageAutoSize(true);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    LoginView.this.dispose();
                }
            });
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCount = e.getClickCount();
                    if(clickCount == 1){
                        LoginView.this.setExtendedState(JFrame.NORMAL);
                    }
                    LoginView.this.setVisible(true);
                }
            });
        }
        //Set default button - login Btn
        getRootPane().setDefaultButton(loginBtn);


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

        springLayout.putConstraint(SpringLayout.WEST,loginBtn,40,SpringLayout.WEST, pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,50,SpringLayout.NORTH, pwdLabel);

        springLayout.putConstraint(SpringLayout.WEST,registerBtn,80,SpringLayout.EAST, loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH,registerBtn,0,SpringLayout.NORTH, loginBtn);
    }

    public JTextField getUserTxt() {
        return userTxt;
    }
    public JPasswordField getPwdField() {
        return pwdField;
    }

    public static void main(String[] args) {
        new LoginView();

    }

}
