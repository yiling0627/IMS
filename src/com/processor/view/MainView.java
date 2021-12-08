package com.processor.view;

import com.processor.handler.MainViewHandler;
import com.processor.req.ProcessorRequest;
import com.processor.res.TableDTO;
import com.processor.service.ProcessorService;
import com.processor.service.impl.ProcessorServiceImpl;
import com.processor.util.DimensionUtil;
import com.processor.view.ext.MainViewTable;
import com.processor.view.ext.MainViewTableModel;
import org.jfree.data.Value;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Vector;

/**
 * Author Yiling
 */

public class MainView extends JFrame {
    Font font = new Font("Cooper Black", Font.PLAIN, 16);
    Font font2 = new Font("Cooper Black", Font.PLAIN, 20);

    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton delBtn = new JButton("Delete");
    JButton chartBtn = new JButton("Analyze");
    JButton sortBtn = new JButton("Sort");
    JTextField searchTxt = new JTextField(20);
    JButton searchBtn = new JButton("Search");

    MainViewTable mainViewTable = new MainViewTable();
    MainViewHandler mainViewHandler = new MainViewHandler(this);

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("Prev");
    JButton nextBtn = new JButton("Next");

    String user_name;

    private int pageNow = 1; // current page
    private int pageSize = 10; // 10 records per page

    public MainView(String user) {
        super("Information Management System");
        user_name = user;
        Container contentPane = getContentPane();

        //North Component
        layoutNorth(contentPane);
        //Middle Component
        layoutCenter(contentPane);
        //South Component
        layoutSouth(contentPane);

        //Set JFrame size
        setBounds(DimensionUtil.getBounds());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setIconImage(new ImageIcon("src/com/processor/image/icon.png").getImage());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

    }

    private void layoutNorth(Container contentPane) {
        addBtn.addActionListener(mainViewHandler);
        updateBtn.addActionListener(mainViewHandler);
        delBtn.addActionListener(mainViewHandler);
        chartBtn.addActionListener(mainViewHandler);
        sortBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(chartBtn);
        northPanel.add(sortBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        addBtn.setFont(font);
        updateBtn.setFont(font);
        delBtn.setFont(font);
        chartBtn.setFont(font);
        sortBtn.setFont(font);
        searchTxt.setFont(font2);
        searchBtn.setFont(font);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }
    private void layoutCenter(Container contentPane) {
//        Vector<Vector<Object>> data = new Vector<>();
//
//        Vector<Object> row1 = new Vector<>();
//        row1.addElement("7321101255");
//        row1.addElement("Maggie");
//        row1.addElement("300,000");
//        row1.addElement("3.125");
//        row1.addElement("Fixed-30Yrs");
//        row1.addElement("2021-10-28");
//
//        data.addElement(row1);
//
        TableDTO tableDTO = getTableDTO();
        Vector<Vector<Object>> data = tableDTO.getData();
        MainViewTableModel mainViewTableModel = MainViewTableModel.assemble(data);
        mainViewTable.setDataModel(mainViewTableModel);
        mainViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
    }
    private void layoutSouth(Container contentPane) {
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        preBtn.setFont(font);
        nextBtn.setFont(font);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }

    private TableDTO getTableDTO(){
        ProcessorService processorService = new ProcessorServiceImpl();
        ProcessorRequest request = new ProcessorRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setName(user_name);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = processorService.retrieveProcessor(request);
        return  tableDTO;
    }

    public void reloadTable(){
        TableDTO tableDTO = getTableDTO();
        MainViewTableModel.updateModel(tableDTO.getData());
        mainViewTable.renderRule();
//        showPreNext(tableDTO.getTotalCount());
    }

    public void sortTable(){
        TableDTO tableDTO = getTableDTO();
        MainViewTableModel.updateModel(tableDTO.sort().getData());
        mainViewTable.renderRule();
    }

//    public void deleteTable() {
//        Vector<Object> selectedLoans = this.getSelectedLoanNos();
//
//        MainViewTableModel.updateModel();
//        mainViewTable.renderRule();
//    }

    public Vector<Integer> getQuarterData(){
        TableDTO tableDTO = getTableDTO();
        Vector<Integer> v = tableDTO.getQua();
        return v;
    }

    public Vector<Object> getSelectedLoanNos(){
        int[] selectedRow = mainViewTable.getSelectedRows();
        Vector<Object> loans = new Vector<>();
        for (int i = 0; i < selectedRow.length; i++){
            int rowIndex = selectedRow[i];
            Object idObj = mainViewTable.getValueAt(rowIndex,0);
            loans.add(idObj);
        }
        return loans;
    }

    public void setPageNow(int pageNow){
        this.pageNow = pageNow;
    }

    public int getPageNow(){
        return pageNow;
    }

    public static void main(String[] args) {
    }


}
