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
import mysql.core.CarOwner;

public class CarOwnerTableModel extends AbstractTableModel {
    public static final int OBJECT_COL = -1;
    private static final int BRANCH_COL = 0;
    private static final int AGENT_COL = 1;
    private static final int OWNER_FIRSTNAME_COL = 2;
    private static final int OWNER_LASTNAME_COL = 3;
    private static final int CONTACT_NUMBER_COL = 4;
    private static final int CARS_OWNED_COL = 5;
    
    private String[] columnNames = {"Branch","Agent","Owner FirstName","Owner LastName","Contact Number","Cars Owned"};
    private ArrayList<CarOwner> owners;
    
    public CarOwnerTableModel(ArrayList<CarOwner> list){
        this.owners = list;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return owners.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row,int col){
        CarOwner owner = owners.get(row);
        
        switch(col){
            case BRANCH_COL:
                return owner.getBranch();
            case AGENT_COL:
                return owner.getAgent();
            case OWNER_FIRSTNAME_COL:
                return owner.getOwnerFirstName();
            case OWNER_LASTNAME_COL:
                return owner.getOwnerLastName();
            case CONTACT_NUMBER_COL:
                return owner.getContactNumber();
            case CARS_OWNED_COL:
                return owner.getOwnedCars();
            case OBJECT_COL:
                return owner;
            default:
                return owner.getOwnerID();
        }
    }
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }
}
