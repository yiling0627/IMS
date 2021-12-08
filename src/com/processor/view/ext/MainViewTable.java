package com.processor.view.ext;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable {

    public MainViewTable (){
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 18));
        tableHeader.setForeground(Color.red);
        //table body
        setFont(new Font(null,Font.PLAIN,14));
        setForeground(Color.black);
        setGridColor(Color.BLACK);
        setRowHeight(30);
        //multiple select
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    }

    public void setDataModel(MainViewTableModel mainViewTableModel){
        this.setModel(mainViewTableModel);
    }

    public void renderRule(){
        //render rules
        Vector<String> columns = MainViewTableModel.getColumns();
        MainViewCellRender render = new MainViewCellRender();
        for (int i = 0; i < columns.size(); i++){
            TableColumn tableColumn = getColumn(columns.get(i));
            tableColumn.setCellRenderer(render);
            if(i == 0){
                tableColumn.setPreferredWidth(50);
            }
        }
    }
}
