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
import mysql.core.Branch;
import javax.swing.table.AbstractTableModel;

public class BranchTableModel extends AbstractTableModel {
    public static final int OBJECT_COL = -1;
    private static final int BRANCH_NAME_COL = 0;
    private static final int BRANCH_MNGRFIRST_COL = 1;
    private static final int BRANCH_MNGRLAST_COL = 2;
    private static final int CONTACT_NUM_COL = 3;
    
    private String[] columnNames = {"Branch Name","Manager First Name","Manager Last Name","Contact Number"};
    private ArrayList<Branch> branches;
    
    public BranchTableModel(ArrayList<Branch> list){
        this.branches = list;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return branches.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row,int col){
        Branch branch = branches.get(row);
        
        switch(col){
            case BRANCH_NAME_COL:
                return branch.getBranchName();
            case BRANCH_MNGRFIRST_COL:
                return branch.getBranchManagerFirst();
            case BRANCH_MNGRLAST_COL:
                return branch.getBranchManagerLast();
            case CONTACT_NUM_COL:
                return branch.getContactNumber();
            case OBJECT_COL:
                return branch;
            default: 
                return branch.getBranchID();
        }
    }
        
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }
}
