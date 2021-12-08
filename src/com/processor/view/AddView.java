package com.processor.view;

import com.processor.entity.LoanDO;
import com.processor.handler.AddHandler;

import javax.swing.*;
import java.awt.*;

public class AddView extends JDialog {
    Font font = new Font("Cooper Black", Font.PLAIN, 15);

    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,20));
    JLabel loan_number_label = new JLabel("Loan#:",JLabel.RIGHT);
    JTextField loan_number_text = new JTextField();
    JLabel name_label = new JLabel("Name:",JLabel.RIGHT);
    JTextField name_text = new JTextField();
    JLabel rate_label = new JLabel("Rate:",JLabel.RIGHT);
    JTextField rate_text = new JTextField();
    JLabel loan_amount_label = new JLabel("Loan$:",JLabel.RIGHT);
    JTextField loan_amount_text = new JTextField();
    JLabel program_label = new JLabel("Program:",JLabel.RIGHT);
//    JTextField program_text = new JTextField();
    JComboBox program_box = new JComboBox();
    JLabel expiration_label = new JLabel("Expire:",JLabel.RIGHT);
    JTextField expiration_text = new JTextField();

    JButton btn = new JButton("Submit");

    MainView mainViewCurrent;
    AddHandler addHandler;


    public AddView(MainView mainView){
        super(mainView,"Add Loan Information", true);
        addHandler = new AddHandler(this,mainView);
        mainViewCurrent = mainView;
        Container contentPane = getContentPane();

        layoutContent(contentPane);

        setSize(350,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    private void layoutContent(Container contentPane) {
        loan_number_label.setPreferredSize(new Dimension(80,30));
        loan_number_text.setPreferredSize(new Dimension(200, 30));
        loan_number_label.setFont(font);
        loan_number_text.setFont(font);
        jPanel.add(loan_number_label);
        jPanel.add(loan_number_text);

        name_label.setPreferredSize(new Dimension(80,30));
        name_text.setPreferredSize(new Dimension(200, 30));
        name_label.setFont(font);
        name_text.setFont(font);
        jPanel.add(name_label);
        jPanel.add(name_text);

        rate_label.setPreferredSize(new Dimension(80,30));
        rate_text.setPreferredSize(new Dimension(200, 30));
        rate_label.setFont(font);
        rate_text.setFont(font);
        jPanel.add(rate_label);
        jPanel.add(rate_text);

        loan_amount_label.setPreferredSize(new Dimension(80,30));
        loan_amount_text.setPreferredSize(new Dimension(200, 30));
        loan_amount_label.setFont(font);
        loan_amount_text.setFont(font);
        jPanel.add(loan_amount_label);
        jPanel.add(loan_amount_text);

        program_label.setPreferredSize(new Dimension(80,30));
//        program_text.setPreferredSize(new Dimension(200, 30));
        program_box.addItem("Fixed-15Yrs");
        program_box.addItem("Fixed-20Yrs");
        program_box.addItem("Fixed-30Yrs");
//        program_text.setPreferredSize(new Dimension(200, 30));
        program_box.setPreferredSize(new Dimension(200, 30));
        program_label.setFont(font);
//        program_text.setFont(font);
        program_box.setFont(font);
        jPanel.add(program_label);
//        jPanel.add(program_text);
        jPanel.add(program_box);

        expiration_label.setPreferredSize(new Dimension(80,30));
        expiration_text.setPreferredSize(new Dimension(200, 30));
        expiration_label.setFont(font);
        expiration_text.setFont(font);
        jPanel.add(expiration_label);
        jPanel.add(expiration_text);

        btn.setFont(font);
        btn.addActionListener(addHandler);
        jPanel.add(btn);

        contentPane.add(jPanel);
    }

    public LoanDO buildLoanDO(){
        LoanDO loanDO = new LoanDO();
        loanDO.setUser_name(mainViewCurrent.user_name);
        loanDO.setLoan_number(loan_number_text.getText());
        loanDO.setBor_name(name_text.getText());
        loanDO.setRate(rate_text.getText());
        loanDO.setLoan_amount(loan_amount_text.getText());
//        loanDO.setProgram(program_text.getText());
        loanDO.setProgram(program_box.getSelectedItem().toString());
        loanDO.setExpiration(expiration_text.getText());
//        try {
//            loanDO.setExpiration(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(expiration_text)));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return loanDO;
    }

    public static void main(String[] args) {
    }

}
