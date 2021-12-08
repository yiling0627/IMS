package com.processor.handler;

import com.processor.entity.LoanDO;
import com.processor.service.ProcessorService;
import com.processor.service.impl.ProcessorServiceImpl;
import com.processor.view.MainView;
import com.processor.view.UpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateHandler implements ActionListener {
    UpdateView updateView;
    MainView mainView;

    public UpdateHandler (UpdateView updateView, MainView mainView){
        this.mainView = mainView;
        this.updateView = updateView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("Update".equals(text)){
            ProcessorService processorService = new ProcessorServiceImpl();
            LoanDO loanDO = updateView.buildUpdatedLoanDO();
            boolean result = processorService.update(loanDO);
            if (result){
                // reload table
                mainView.reloadTable();
                updateView.dispose();
            } else {
                JOptionPane.showMessageDialog(updateView,"Update Failure!");
            }
        }
    }
}
