package com.processor.handler;

import com.processor.entity.LoanDO;
import com.processor.service.ProcessorService;
import com.processor.service.impl.ProcessorServiceImpl;
import com.processor.view.AddView;
import com.processor.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddHandler implements ActionListener {
    private AddView addView;
    private MainView mainView;

    public AddHandler (AddView addView, MainView mainView) { this.addView = addView; this.mainView = mainView;}

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("Submit".equals(text)){
            ProcessorService processorService = new ProcessorServiceImpl();
            LoanDO loanDO = addView.buildLoanDO();
            boolean addResult = processorService.add(loanDO);
            if (addResult){
                // reload table
                mainView.reloadTable();
                addView.dispose();
            } else {
                JOptionPane.showMessageDialog(addView,"Add Failure!");
            }
        }

    }
}
