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
import mysql.core.Bookings;

public class BookingsTableModel extends AbstractTableModel{
    public static final int OBJECT_COL = -1;
    private static final int AGENT_COL = 0;
    private static final int CAR_COL = 1;
    private static final int CUSTOMER_COL = 2;
    private static final int DATE_BOOKED_COL = 3;
    private static final int DATE_RENTED_COL = 4;
    
    private String[] columnNames = {"Agent","Car","Customer","Date Booked","Date Rented"};
    private ArrayList<Bookings> bookings;
    
    public BookingsTableModel(ArrayList<Bookings> list){
        this.bookings = list;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return bookings.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row,int col){
        Bookings book = bookings.get(row);
        
        switch(col){
            case AGENT_COL:
                return book.getAgentName();
            case CAR_COL:
                return book.getCarName();
            case CUSTOMER_COL:
                return book.getCustomerName();
            case DATE_BOOKED_COL:
                return book.getLSDateBooked();
            case DATE_RENTED_COL:
                return book.getLSDateRented();
            case OBJECT_COL:
                return book;
            default:
                return book.getBookingID();
        }
    }
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }
}
