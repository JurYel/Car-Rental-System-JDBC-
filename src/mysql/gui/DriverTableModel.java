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
import mysql.core.Driver;

public class DriverTableModel extends AbstractTableModel {
    public static final int OBJECT_COL = -1;
    private static final int BRANCH_COL = 0;
    private static final int AGENT_COL = 1;
    private static final int CAR_COL = 2;
    private static final int DRIVER_FIRSTNAME_COL = 3;
    private static final int DRIVER_LASTNAME_COL = 4;
    private static final int CONTACT_NUMBER_COL = 5;
    
    private String[] columnNames = {"Branch","Agent","Car Name","Driver FirstName","Driver LastName","Contact Number"};
    private ArrayList<Driver> drivers;
    
    public DriverTableModel(ArrayList<Driver> list){
        this.drivers = list;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return drivers.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row,int col){
        Driver driver = drivers.get(row);
        
        switch(col){
            case BRANCH_COL:
                return driver.getBranchName();
            case AGENT_COL:
                return driver.getAgent();
            case CAR_COL:
                return driver.getCarName();
            case DRIVER_FIRSTNAME_COL:
                return driver.getDriverFirstName();
            case DRIVER_LASTNAME_COL:
                return driver.getDriverLastName();
            case CONTACT_NUMBER_COL:
                return driver.getContactNumber();
            case OBJECT_COL:
                return driver;
            default:
                return driver.getDriverID();
        }
    }
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }
}
