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
import mysql.core.Agent;

public class AgentTableModel extends AbstractTableModel{
    public static final int OBJECT_COL = -1;
    private static final int BRANCH_NAME_COL = 0;
    private static final int AGENT_FIRSTNAME_COL = 1;
    private static final int AGENT_LASTNAME_COL = 2;
    private static final int CONTACT_NUMBER_COL = 3;
    
    private String[] columnNames = {"Branch","Agent FirstName","Agent LastName","Contact Number"};
    private ArrayList<Agent> agents;
    
    public AgentTableModel(ArrayList<Agent> list){
        this.agents = list;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return agents.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row,int col){
        Agent agent = agents.get(row);
        
        switch(col){
            case BRANCH_NAME_COL:
                return agent.getBranchName();
            case AGENT_FIRSTNAME_COL:
                return agent.getFirstName();
            case AGENT_LASTNAME_COL:
                return agent.getLastName();
            case CONTACT_NUMBER_COL:
                return agent.getContactNumber();
            case OBJECT_COL:
                return agent;
            default: 
                return agent.getAgentID();
        }
    }
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }
}
