/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql.gui;

/**
 *
 * @author Jur Yel
 */
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import mysql.core.BookingsHistory;

public class BookingsHistoryTableModel extends AbstractTableModel{
    public static final int OBJECT_COL = -1;
    private static final int EMPLOYEE_COL = 0;
    private static final int CUSTOMER_COL = 1;
    private static final int ACTION_COL = 2;
    private static final int CAR_COL = 3;
    private static final int DATE_COL = 4;
    
    private String[] columnNames = {"Administrator","Customer Name","Action Performed","Car Name","Date/Time Performed"};
    private ArrayList<BookingsHistory> records;
    
    public BookingsHistoryTableModel(ArrayList<BookingsHistory> list){
        this.records = list;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return records.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row,int col){
        BookingsHistory bookHistory = records.get(row);
        
        switch(col){
            case EMPLOYEE_COL:
                return bookHistory.getEmployeeName();
            case ACTION_COL:
                return bookHistory.getActionPerformed();
            case CUSTOMER_COL:
                return bookHistory.getCustomerName();
            case CAR_COL:
                return bookHistory.getCarName();
            case DATE_COL:
                return bookHistory.getDatePerformed();
            case OBJECT_COL:
                return bookHistory;
            default:
                return bookHistory.getHistoryID();
        }
    }
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }
}
