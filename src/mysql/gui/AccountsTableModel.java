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
import mysql.core.Account;

public class AccountsTableModel extends AbstractTableModel{
    public static final int OBJECT_COL = -1;
    private static final int EMPLOYEE_ID_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int LAST_NAME_COL = 2;
    private static final int PASSWORD_COL = 3;
    
    private String[] columnNames = {"Employee ID","First Name","Last Name","Password"};
    private ArrayList<Account> accounts;
    
    public AccountsTableModel(ArrayList<Account> list){
        this.accounts = list;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return accounts.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row,int col){
        Account acc = accounts.get(row);
        
        switch(col){
            case EMPLOYEE_ID_COL:
                return acc.getAccountID();
            case FIRST_NAME_COL:
                return acc.getFirstName();
            case LAST_NAME_COL:
                return acc.getLastName();
            case PASSWORD_COL:
                return acc.getPassword();
            case OBJECT_COL:
                return acc;
            default:
                return acc.getAccountID();
        }
    }
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }
}
