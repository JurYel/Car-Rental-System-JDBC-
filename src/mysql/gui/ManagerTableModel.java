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
import mysql.core.Manager;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ManagerTableModel extends AbstractTableModel{
    public static final int OBJECT_COL = -1;
    private static final int BRANCH_NAME_COL = 0;
    private static final int MNGR_LASTNAME_COL = 1;
    private static final int MNGR_FIRSTNAME_COL = 2;
    private static final int CONTACT_COL = 3;
    
    private String[] columnNames = {"Branch","Manager FirstName","Manager LastName","Contact Number"};
    private ArrayList<Manager> managers;
    
    public ManagerTableModel(ArrayList<Manager> list ){
        this.managers  = list;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return managers.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row,int col){
        Manager manager = managers.get(row);
        
        switch(col){
            case BRANCH_NAME_COL:
                return manager.getBranchName();
            case MNGR_LASTNAME_COL:
                return manager.getManagerLastName();
            case MNGR_FIRSTNAME_COL:
                return manager.getManagerFirstName();
            case CONTACT_COL:
                return manager.getContactNumber();
            case OBJECT_COL:
                return manager;
            default:
                return manager.getManagerID();
        }
    }
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }
}