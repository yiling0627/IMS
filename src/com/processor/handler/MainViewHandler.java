package com.processor.handler;

import com.processor.entity.LoanDO;
import com.processor.service.ProcessorService;
import com.processor.service.impl.ProcessorServiceImpl;
import com.processor.view.AddView;
import com.processor.view.BarChart;
import com.processor.view.MainView;
import com.processor.view.UpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MainViewHandler implements ActionListener {
    private MainView mainView;
    public MainViewHandler (MainView mainView) { this.mainView = mainView; }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("Add".equals(text)){
            new AddView(mainView);
        }



        else if ("Update".equals(text)){
            Vector<Object> selectedLoans = mainView.getSelectedLoanNos();
            if(selectedLoans.size() != 1){
                JOptionPane.showMessageDialog(mainView,"Only can update one row!");
                return;
            }
            new UpdateView(mainView,selectedLoans.get(0).toString());
        }



        else if ("Delete".equals(text)){
            Vector<Object> selectedLoans = mainView.getSelectedLoanNos();
            if(selectedLoans.size() == 0){
                JOptionPane.showMessageDialog(mainView,"Please select row!");
                return;
            }
            ProcessorService processorService = new ProcessorServiceImpl();
            boolean deleteResult = processorService.delete(selectedLoans);
            if (deleteResult){
                // reload table
                mainView.reloadTable();
            } else {
                JOptionPane.showMessageDialog(mainView,"Delete Failure!");
            }
        }



        else if ("Analyze".equals(text)){
            new BarChart(mainView);
        }


        else if ("Sort".equals(text)){
            mainView.sortTable();
        }


        else if ("Search".equals(text)){
            mainView.setPageNow(1);
            mainView.reloadTable();
        } else if ("Prev".equals(text)){
            mainView.setPageNow(mainView.getPageNow() - 1);
            mainView.reloadTable();
        } else if ("Next".equals(text)){
            mainView.setPageNow(mainView.getPageNow() + 1);
            mainView.reloadTable();
        }
    }
}
