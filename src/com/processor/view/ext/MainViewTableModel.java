package com.processor.view.ext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("Loan#");
        columns.addElement("Borrower");
        columns.addElement("Loan$");
        columns.addElement("Rate");
        columns.addElement("Program");
        columns.addElement("Expiration");
    }

    private MainViewTableModel (){
        super(null, columns);
    }

    private static MainViewTableModel mainViewTableModel = new MainViewTableModel();

    public static MainViewTableModel assemble(Vector<Vector<Object>> data){
        mainViewTableModel.setDataVector(data,columns);
        return mainViewTableModel;
    }

    public static void updateModel(Vector<Vector<Object>> data){
        mainViewTableModel.setDataVector(data,columns);
    }

    public static Vector<String> getColumns(){
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
