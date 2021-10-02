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
import mysql.core.Customer;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
public class CustomerTableModel extends AbstractTableModel{
    public static final int OBJECT_COL = -1;
    private static final int FIRST_COL = 0;
    private static final int LAST_COL = 1;
    private static final int CONTACT_COL = 2;
    
    private String[] columnNames = {"First Name","Last Name","Contact Number"};
    private ArrayList<Customer> customers;
    
    public CustomerTableModel(ArrayList<Customer> list){
        this.customers = list;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return customers.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row,int col){
        Customer customer = customers.get(row);
        
        switch(col){
            case FIRST_COL:
                return customer.getFirstName();
            case LAST_COL:
                return customer.getLastName();
            case CONTACT_COL:
                return customer.getContactNumber();
            case OBJECT_COL:
                return customer;
            default: 
                return customer.getCustomerID();
        }
    }
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }
    
}
