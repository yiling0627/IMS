package com.processor.view;

import com.processor.entity.LoanDO;
import com.processor.handler.UpdateHandler;
import com.processor.service.ProcessorService;
import com.processor.service.impl.ProcessorServiceImpl;

import javax.swing.*;
import java.awt.*;

public class UpdateView extends JDialog {
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
    JTextField program_text = new JTextField();
    JLabel expiration_label = new JLabel("Expire:",JLabel.RIGHT);
    JTextField expiration_text = new JTextField();

    JButton btn = new JButton("Update");
    UpdateHandler updateHandler;


    LoanDO selected;

    public UpdateView(MainView mainView,String selectedLoan){
        super(mainView,"Update Loan Information", true);
        ProcessorService processorService = new ProcessorServiceImpl();
        selected = processorService.getByLoanNo(selectedLoan);
        updateHandler = new UpdateHandler(this, mainView);

        Container contentPane = getContentPane();

        layoutContent(contentPane);

        setSize(350,450);
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
        loan_number_text.setText(selected.getLoan_number());
        jPanel.add(loan_number_label);
        jPanel.add(loan_number_text);

        name_label.setPreferredSize(new Dimension(80,30));
        name_text.setPreferredSize(new Dimension(200, 30));
        name_label.setFont(font);
        name_text.setFont(font);
        name_text.setText(selected.getBor_name());
        jPanel.add(name_label);
        jPanel.add(name_text);

        rate_label.setPreferredSize(new Dimension(80,30));
        rate_text.setPreferredSize(new Dimension(200, 30));
        rate_label.setFont(font);
        rate_text.setFont(font);
        rate_text.setText(selected.getRate());
        jPanel.add(rate_label);
        jPanel.add(rate_text);

        loan_amount_label.setPreferredSize(new Dimension(80,30));
        loan_amount_text.setPreferredSize(new Dimension(200, 30));
        loan_amount_label.setFont(font);
        loan_amount_text.setFont(font);
        loan_amount_text.setText(selected.getLoan_amount());
        jPanel.add(loan_amount_label);
        jPanel.add(loan_amount_text);

        program_label.setPreferredSize(new Dimension(80,30));
        program_text.setPreferredSize(new Dimension(200, 30));
        program_label.setFont(font);
        program_text.setFont(font);
        program_text.setText(selected.getProgram());
        program_text.setEnabled(false);
        jPanel.add(program_label);
        jPanel.add(program_text);

        expiration_label.setPreferredSize(new Dimension(80,30));
        expiration_text.setPreferredSize(new Dimension(200, 30));
        expiration_label.setFont(font);
        expiration_text.setFont(font);
        expiration_text.setText(selected.getExpiration());
        jPanel.add(expiration_label);
        jPanel.add(expiration_text);

        btn.setFont(font);
        btn.addActionListener(updateHandler);
        jPanel.add(btn);

        contentPane.add(jPanel);
    }

    public LoanDO buildUpdatedLoanDO() {
        LoanDO loanDO = new LoanDO();
        loanDO.setId(selected.getId());
        loanDO.setLoan_number(loan_number_text.getText());
        loanDO.setBor_name(name_text.getText());
        loanDO.setRate(rate_text.getText());
        loanDO.setLoan_amount(loan_amount_text.getText());
        loanDO.setProgram(program_text.getText());
        loanDO.setExpiration(expiration_text.getText());
        return loanDO;
    }

}
